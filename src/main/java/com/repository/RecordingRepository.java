package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Recording;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long> {

}
