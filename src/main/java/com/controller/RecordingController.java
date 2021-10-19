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

import com.dto.RecordingDTO;
import com.service.RecordingService;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/recording")
public class RecordingController {
	
	@Autowired
	RecordingService recordingService;
	
	@GetMapping
	public ResponseEntity<List<RecordingDTO>> getAllRecordings() throws Exception {
		List<RecordingDTO> recordings = this.recordingService.getAllRecordings();
		return new ResponseEntity<List<RecordingDTO>>(recordings, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RecordingDTO> getRecording(@PathVariable long id) throws Exception {
		RecordingDTO recording = this.recordingService.getRecordingById(id);
		return new ResponseEntity<RecordingDTO>(recording, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> saveRecording(@RequestBody RecordingDTO recordingDTO) throws Exception {
		 this.recordingService.saveRecording(recordingDTO);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<HttpStatus> updateRecording(@RequestBody RecordingDTO recordingDTO,@PathVariable long id) throws Exception {
		 this.recordingService.updateRecording(id,recordingDTO);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletRecording(@PathVariable long id) throws Exception {
		 this.recordingService.deleteRecording(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
