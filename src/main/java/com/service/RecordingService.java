package com.service;

import java.util.List;

import com.dto.RecordingDTO;

public interface RecordingService {
	
	RecordingDTO getRecordingById(long id) throws Exception;
	List<RecordingDTO> getAllRecordings() throws Exception;
	void saveRecording(RecordingDTO track);
	void updateRecording(long id,RecordingDTO track) throws Exception;
	void deleteRecording(long id) throws Exception;
}
