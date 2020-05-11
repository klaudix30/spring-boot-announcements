package com.example.announ.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.announ.entities.Announcement;
import com.example.announ.services.AnnouncementService;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

@RestController
@RequestMapping("/announcements")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AnnouncementController {
	
	private final AnnouncementService announService;
	
	@GetMapping("")
	public ResponseEntity<List<Announcement>> getAllAnnouncements(){
		return ResponseEntity.ok(announService.findAll());
		
	}
	@GetMapping("/filtred/{name}")
	public ResponseEntity<List<Announcement>> getAnnouncementBySubject(@PathVariable String name){
		return ResponseEntity.ok(announService.getFilteredAnnoun(name));
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Announcement>getAnnouncementById(@PathVariable Long id){
		Optional<Announcement>announ=announService.findById(id);
		if(!announ.isPresent()) {
			log.error("Id"+id+"does not exist ");
			return ResponseEntity.badRequest().build();
		}else 
			return ResponseEntity.ok(announ.get());
		
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		announService.uploadImage(file);
	}
	@PostMapping("")
	public ResponseEntity<Announcement>createAnnouncement(@Valid @RequestBody Announcement announ) {
		Announcement createdAnnoun=announService.createAnnoun(announ);
		return ResponseEntity.ok(announService.createAnnoun(createdAnnoun));
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<Announcement> updateAnnouncement(@PathVariable Long id, @Valid @RequestBody Announcement announ){
		if(!announService.findById(id).isPresent()) {
			log.error("id"+id+"does not exist");
			return ResponseEntity.badRequest().build();
		}
		else
			return ResponseEntity.ok(announService.createAnnoun(announ));
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Announcement>deleteAnnouncement(@PathVariable Long id) {
		if(!announService.findById(id).isPresent()) {
			log.error("id"+id+"does not exist");
			return ResponseEntity.badRequest().build();
		}
		else
		{
			announService.deleteById(id);
			return ResponseEntity.ok().build();
		}
	}

	
}
