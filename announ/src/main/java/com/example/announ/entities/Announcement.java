package com.example.announ.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class Announcement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@PositiveOrZero
	private Long price;
	
	@NotEmpty
	@Size(min = 4, max = 20)
	private String subject;
	
	@NotEmpty
	@Size(min=10,max=100)
	private String description;
	
	@CreationTimestamp
	private Date createdAt;
	
	@Lob
	private byte[] pic;
	
	public void setPic(byte[] pic) {
		this.pic=pic;
	}

	
	
}
