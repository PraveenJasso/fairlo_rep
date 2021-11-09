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

import com.dto.RecordingDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Artist;
import com.model.Track;
import com.service.RecordingService;

@SpringBootTest
@org.junit.jupiter.api.Tag("fairlo")
@AutoConfigureMockMvc(addFilters = false)
public class RecordingControllerTest {
	@MockBean
	private RecordingService recordingService;
	@Autowired
	private MockMvc mockMvc;
	private static final String URL = "/recording";
	private static final String URL_WITH_ID = "/recording/0";

	private static final int STATUS_OK = 200;

	@Test
	public void testgetAllRecordings() throws Exception {
		this.recordingService.getAllRecordings();
		ResultActions actions = this.mockMvc.perform(
				get(URL).contentType(MediaType.APPLICATION_JSON).content(convertBeanToJson(getRecordingDetails())))
				.andDo(print());
		assertEquals(STATUS_OK, actions.andReturn().getResponse().getStatus(), "Test success Get Tracks");
	}

	@Test
	public void testgetRecording() throws Exception {
		this.recordingService.getRecordingById(0);
		ResultActions actions = this.mockMvc.perform(get(URL_WITH_ID).contentType(MediaType.APPLICATION_JSON)
				.content(convertBeanToJson(populateRecording()))).andDo(print());
		assertEquals(STATUS_OK, actions.andReturn().getResponse().getStatus(), "Test success Get Track by Id");
	}

	private String convertBeanToJson(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

	private static List<RecordingDTO> getRecordingDetails() {
		List<RecordingDTO> recordings = new ArrayList<RecordingDTO>();
		recordings.add(populateRecording());
		return recordings;
	}

	private static RecordingDTO populateRecording() {
		RecordingDTO recording = new RecordingDTO();
		recording.setId(11);
		recording.setDuration("40:35");
		recording.setArtist(populateArtist());
		recording.setTitle("test");
		recording.setTrack(populateTrack());
		return recording;
	}

	private static Artist populateArtist() {
		Artist artist = new Artist();
		artist.setId(11);
		artist.setArea("Sweden");
		artist.setGender("Male");
		artist.setName("Test Artist");
		artist.setShortName("Test");
		return artist;
	}

	private static Track populateTrack() {
		Track track = new Track();
		track.setId(11);
		track.setDuration("40:35");
		track.setPosition("5");
		track.setTitle("test");
		return track;
	}
}
