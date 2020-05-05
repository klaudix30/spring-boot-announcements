package com.example.announ.services;

import java.io.IOException;
import java.util.List;

import com.example.announ.entities.Announcement;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

public interface AnnouncementService {
	
	List<Announcement>findAll();
	Optional<Announcement>findById(Long id);
	Announcement createAnnoun(Announcement announ);
	void deleteById(Long id);
	List<Announcement>getFilteredAnnoun(String subject);
	void uploadImage(MultipartFile file) throws IOException;
	

}
