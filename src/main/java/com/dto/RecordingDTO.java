package com.dto;

import com.model.Artist;
import com.model.Track;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecordingDTO {
	
	private long id;
	
	private String title;
	
	private Artist artist;
	
	private String duration;
	
	private Track track;
}
