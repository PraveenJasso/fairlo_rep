package com.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "recording")
public class Recording {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist artist;
	
	private String duration;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "track_id")
	private Track track;
}
