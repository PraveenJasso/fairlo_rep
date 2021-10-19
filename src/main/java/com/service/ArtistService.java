package com.service;

import java.util.List;

import com.dto.ArtistDTO;

public interface ArtistService {
	
	ArtistDTO getArtistById(long id) throws Exception;
	List<ArtistDTO> getAllArtists() throws Exception;
	void saveArtist(ArtistDTO track);
	void updateArtist(long id,ArtistDTO track) throws Exception;
	void deleteArtist(long id) throws Exception;
}
