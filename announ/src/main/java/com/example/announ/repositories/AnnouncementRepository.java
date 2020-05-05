package com.example.announ.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.announ.entities.Announcement;
public interface AnnouncementRepository extends JpaRepository<Announcement,Long>{

	List<Announcement> findBySubject(String subject);
}
