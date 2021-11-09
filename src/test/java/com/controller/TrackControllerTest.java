package com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.dto.TrackDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.TrackService;

@SpringBootTest
@org.junit.jupiter.api.Tag("fairlo")
@AutoConfigureMockMvc(addFilters = false)
public class TrackControllerTest {
	
	@MockBean
	private TrackService trackService;
	@Autowired
	private MockMvc mockMvc;
	private static final String URL = "/track";
	private static final String URL_WITH_ID = "/track/0";

	private static final int STATUS_OK = 200;

	@Test
	public void testgetAllTrack() throws Exception {
		this.trackService.getAllTracks();
		ResultActions actions = this.mockMvc
				.perform(
						get(URL).contentType(MediaType.APPLICATION_JSON).content(convertBeanToJson(getTrackDetails())))
				.andDo(print());
		assertEquals(STATUS_OK, actions.andReturn().getResponse().getStatus(), "Test success Get Tracks");
	}

	@Test
	public void testgetTrack() throws Exception {
		this.trackService.getTrackById(1);
		ResultActions actions = this.mockMvc.perform(
				get(URL_WITH_ID).contentType(MediaType.APPLICATION_JSON).content(convertBeanToJson(populateTrack())))
				.andDo(print());
		assertEquals(STATUS_OK, actions.andReturn().getResponse().getStatus(), "Test success Get Track by Id");
	}
	private String convertBeanToJson(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
	
	private static List<TrackDTO> getTrackDetails() {

		List<TrackDTO> tracks = new ArrayList<TrackDTO>();
		tracks.add(populateTrack());

		return tracks;
	}
	
	private static TrackDTO populateTrack() {
		TrackDTO track = new TrackDTO();
		track.setId(11);
		track.setDuration("40:35");
		track.setPosition("5");
		track.setTitle("test");
		return track;
	}
}
