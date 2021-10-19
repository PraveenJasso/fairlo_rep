package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dto.RecordingDTO;
import com.dto.TrackDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Track;
import com.repository.TrackRepository;

import ma.glasnost.orika.MapperFacade;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackRepository trackRepository;

	@Autowired
	private transient MapperFacade mapperFacade;

	public TrackDTO getTrackById(long id) throws Exception {
		Optional<Track> track = trackRepository.findById(id);
		if (track.get() == null) {
			throw new Exception("track not found");
		}
		return mapperFacade.map(track.get(), TrackDTO.class);
	}

	public List<TrackDTO> getAllTracks() throws Exception {
		List<Track> tracks = trackRepository.findAll();
		if (CollectionUtils.isEmpty(tracks)) {
			throw new Exception("Tracks not found");
		}
		List<TrackDTO> trackDtos = tracks.stream().map(track -> new ObjectMapper().convertValue(track, TrackDTO.class)).collect(Collectors.toList());
		return trackDtos;
	}

	public void saveTrack(TrackDTO track) {
		trackRepository.save(mapperFacade.map(track, Track.class));
	}

	public void updateTrack(long id, TrackDTO trackDTO) throws Exception {
		Optional<Track> track = trackRepository.findById(id);
		if (track.get() == null) {
			throw new Exception("Track not found to update");
		}
		Track oldTrack = track.get();
		oldTrack.setDuration(trackDTO.getDuration());
		oldTrack.setPosition(trackDTO.getPosition());
		oldTrack.setTitle(trackDTO.getTitle());
		trackRepository.save(oldTrack);
	}

	public void deleteTrack(long id) throws Exception {
		Optional<Track> track = trackRepository.findById(id);
		if (track.get() == null) {
			throw new Exception("Track not found to Delete");
		}
		trackRepository.deleteById(id);
	}

}
