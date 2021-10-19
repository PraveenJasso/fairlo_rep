package com.service;

import java.util.List;

import com.dto.TrackDTO;

public interface TrackService {
	
	TrackDTO getTrackById(long id) throws Exception;
	List<TrackDTO> getAllTracks() throws Exception;
	void saveTrack(TrackDTO track);
	void updateTrack(long id, TrackDTO track) throws Exception;
	void deleteTrack(long id) throws Exception;
}
