package com.example.announ.services;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.announ.repositories.AnnouncementRepository;
import com.example.announ.entities.Announcement;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
	
	private final AnnouncementRepository announRepository;
	private byte[] bytes;
	
	@Override
	public List<Announcement>findAll(){
		return announRepository.findAll();
	}
	
	@Override
	public Optional<Announcement>findById(Long id){
		return announRepository.findById(id);
	}
	
	@Override
	public Announcement createAnnoun(Announcement announ){
		
		announ.setPic(this.bytes);
		return announRepository.save(announ);
		
	}
	@Override
	public void deleteById(Long id) {
		announRepository.deleteById(id);
	}
	@Override
	public List<Announcement>getFilteredAnnoun(String subject){
			
			List<Announcement> list = announRepository.findBySubject(subject);
			if(list.size()>0)
				return list;
			else
				return announRepository.findAll();
	}
	@Override
	public void uploadImage(MultipartFile file) throws IOException {
		this.bytes=file.getBytes();
	}

	
	
	
}
