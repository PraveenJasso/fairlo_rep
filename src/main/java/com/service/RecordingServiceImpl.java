package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dto.ArtistDTO;
import com.dto.RecordingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Recording;
import com.repository.RecordingRepository;

import ma.glasnost.orika.MapperFacade;

@Service
public class RecordingServiceImpl implements RecordingService {

	@Autowired
	private RecordingRepository recordingRepository;

	@Autowired
	private transient MapperFacade mapperFacade;

	public RecordingDTO getRecordingById(long id) throws Exception {
		Optional<Recording> record = recordingRepository.findById(id);
		if (record.get() == null) {
			throw new Exception("Record not found");
		}
		return mapperFacade.map(record.get(), RecordingDTO.class);
	}

	public List<RecordingDTO> getAllRecordings() throws Exception {
		List<Recording> records = recordingRepository.findAll();
		if (CollectionUtils.isEmpty(records)) {
			throw new Exception("Recodings not found");
		}
		List<RecordingDTO> recordDtos = records.stream().map(record -> new ObjectMapper().convertValue(record, RecordingDTO.class)).collect(Collectors.toList());
		return recordDtos;
	}

	public void saveRecording(RecordingDTO record) {
		recordingRepository.save(mapperFacade.map(record, Recording.class));
	}

	public void updateRecording(long id, RecordingDTO recordDTO) throws Exception {
		Optional<Recording> recording = recordingRepository.findById(id);
		if (recording.get() == null) {
			throw new Exception("Recording not found to update");
		}
		Recording oldRecord = recording.get();
		oldRecord.setDuration(recordDTO.getDuration());
		oldRecord.setTitle(recordDTO.getTitle());
		recordingRepository.save(oldRecord);
	}

	public void deleteRecording(long id) throws Exception {
		Optional<Recording> recording = recordingRepository.findById(id);
		if (recording.get() == null) {
			throw new Exception("Recording not found to delete");
		}
		recordingRepository.deleteById(id);
	}

}
