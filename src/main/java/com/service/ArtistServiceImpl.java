package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dto.ArtistDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Artist;
import com.repository.ArtistRepository;

import ma.glasnost.orika.MapperFacade;

@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private transient MapperFacade mapperFacade;

	public ArtistDTO getArtistById(long id) throws Exception {
		Optional<Artist> artist = artistRepository.findById(id);
		if (artist.get() == null) {
			throw new Exception("Artist not found");
		}
		return mapperFacade.map(artist.get(), ArtistDTO.class);
	}

	public List<ArtistDTO> getAllArtists() throws Exception {
		List<Artist> artists = artistRepository.findAll();
		if (CollectionUtils.isEmpty(artists)) {
			throw new Exception("Artist not found");
		}
		 List<ArtistDTO> artistDTOs = artists.stream().map(artist -> new ObjectMapper().convertValue(artist, ArtistDTO.class)).collect(Collectors.toList());
		return artistDTOs;
	}

	public void saveArtist(ArtistDTO artist) {
		artistRepository.save(mapperFacade.map(artist, Artist.class));
	}

	public void updateArtist(long id, ArtistDTO artistDTO) throws Exception {
		Optional<Artist> artist = artistRepository.findById(id);
		if (artist.get() == null) {
			throw new Exception("Artist not found to update");
		}
		Artist oldArtist = artist.get();
		oldArtist.setArea(artistDTO.getArea());
		oldArtist.setGender(artistDTO.getGender());
		oldArtist.setName(artistDTO.getName());
		oldArtist.setShortName(artistDTO.getShortName());
		artistRepository.save(oldArtist);
	}

	public void deleteArtist(long id) throws Exception {
		Optional<Artist> artist = artistRepository.findById(id);
		if (artist.get() == null) {
			throw new Exception("Artist not found to delete");
		}
		artistRepository.deleteById(id);
	}

}
