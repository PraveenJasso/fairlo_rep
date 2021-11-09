package com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.dto.ArtistDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ArtistService;

@SpringBootTest
@org.junit.jupiter.api.Tag("fairlo")
@AutoConfigureMockMvc(addFilters = false)
public class ArtistControllerTest {

	@MockBean
	private ArtistService artistService;
	@Autowired
	private MockMvc mockMvc;
	private static final String URL = "/artist";
	private static final String URL_WITH_ID = "/artist/0";
	private static final long ID = 1;

	private static final int STATUS_OK = 200;

	@Test
	public void testgetAllArtist() throws Exception {
		this.artistService.getAllArtists();
		ResultActions actions = this.mockMvc
				.perform(
						get(URL).contentType(MediaType.APPLICATION_JSON).content(convertBeanToJson(getArtistDetails())))
				.andDo(print());
		assertEquals(STATUS_OK, actions.andReturn().getResponse().getStatus(), "Test success Get Artists");
	}

	@Test
	public void testgetArtist() throws Exception {
		this.artistService.getArtistById(1);
		ResultActions actions = this.mockMvc.perform(
				get(URL_WITH_ID).contentType(MediaType.APPLICATION_JSON).content(convertBeanToJson(populateArtist())))
				.andDo(print());
		assertEquals(STATUS_OK, actions.andReturn().getResponse().getStatus(), "Test success Get Artists by Id");
	}

	@Test
	public void testSaveArtist() throws Exception {
		this.artistService.saveArtist(populateArtist());
		ResultActions actions = this.mockMvc.perform(post((URL)));
		assertEquals(STATUS_OK, actions.andReturn().getResponse().getStatus(), "Test success Save Artists");
	}

	@Test
	public void testUpdateArtist() throws Exception {
		this.artistService.updateArtist(ID, populateArtist());
		ResultActions actions = this.mockMvc.perform(patch((URL)));
		assertEquals(STATUS_OK, actions.andReturn().getResponse().getStatus(), "Test success Update Artists");
	}

	@Test
	public void testDeleteArtist() throws Exception {
		this.artistService.deleteArtist(ID);
		ResultActions actions = this.mockMvc.perform(delete((URL)));
		assertEquals(STATUS_OK, actions.andReturn().getResponse().getStatus(), "Test success Delete Artists");
	}

	private String convertBeanToJson(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

	/**
	 * Gets the artist details.
	 *
	 * @return the artist details
	 */
	private static List<ArtistDTO> getArtistDetails() {

		List<ArtistDTO> artists = new ArrayList<ArtistDTO>();
		artists.add(populateArtist());

		return artists;
	}

	private static ArtistDTO populateArtist() {
		ArtistDTO artist = new ArtistDTO();
		artist.setId(11);
		artist.setArea("Sweden");
		artist.setGender("Male");
		artist.setName("Test Artist");
		artist.setShortName("Test");
		return artist;
	}
}
