package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(nullable = false, length = 150, unique = true) 
	private String username; 
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50) 
	private String email;
	
	private String oauth; 

	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp 
	private Timestamp createDate;
}
