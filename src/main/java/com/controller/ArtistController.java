package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.ArtistDTO;
import com.service.ArtistService;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/artist")
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping
	public ResponseEntity<List<ArtistDTO>> getAllArtist() throws Exception {
		List<ArtistDTO> artists = this.artistService.getAllArtists();
		return new ResponseEntity<List<ArtistDTO>>(artists, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ArtistDTO> getArtist(@PathVariable long id) throws Exception {
		ArtistDTO artist = this.artistService.getArtistById(id);
		return new ResponseEntity<ArtistDTO>(artist, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> saveArtist(@RequestBody ArtistDTO artistDTO) throws Exception {
		 this.artistService.saveArtist(artistDTO);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<HttpStatus> updateArtist(@RequestBody ArtistDTO artistDTO,@PathVariable long id) throws Exception {
		 this.artistService.updateArtist(id,artistDTO);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteArtist(@PathVariable long id) throws Exception {
		 this.artistService.deleteArtist(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
