package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량데이터
	private String content; // 내용이 커서 섬머노트 라이브러리를 사용할 것이다. 일반적인 글이 디자인될 때,  <html>태그가 섞여서 디자인되서 
	
	@ColumnDefault("0")
	private Long count; // 조회수
	
	@ManyToOne // Many = Board , One = User , 즉 한명이 여러개의 게시글 작성 O, But, 하나의 게시글이 여러명한테 쓰여질 수 x
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. 그래서 F.K 써야하는데, 자바는 오브젝트를 저장할 수 있다. 자바 프로그램에서 DB 자료형에 맞춰서 만든다.
	
	@CreationTimestamp
	private Timestamp createDate;
}
