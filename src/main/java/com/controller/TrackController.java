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

import com.dto.TrackDTO;
import com.service.TrackService;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/track")
public class TrackController {
	
	@Autowired
	private TrackService trackService;
	
	@GetMapping
	public ResponseEntity<List<TrackDTO>> getAllTracks() throws Exception {
		List<TrackDTO> tracks = this.trackService.getAllTracks();
		return new ResponseEntity<List<TrackDTO>>(tracks, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TrackDTO> getTrack(@PathVariable long id) throws Exception {
		TrackDTO track = this.trackService.getTrackById(id);
		return new ResponseEntity<TrackDTO>(track, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> saveTrack(@RequestBody TrackDTO trackDTO) throws Exception {
		 this.trackService.saveTrack(trackDTO);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<HttpStatus> updateTrack(@RequestBody TrackDTO trackDTO,@PathVariable long id) throws Exception {
		 this.trackService.updateTrack(id,trackDTO);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteTrack(@PathVariable long id) throws Exception {
		 this.trackService.deleteTrack(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
