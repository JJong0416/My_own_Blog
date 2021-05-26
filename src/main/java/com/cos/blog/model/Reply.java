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

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor 
@Builder
@Entity
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long id;
	
	@Column(nullable = false, length = 200)
	private String content;

	// 누가 어느테이블에 답변인지 알아야한다. 그래서 연관관계를 맺어줘야함 그게 ManyToOne 
	@ManyToOne // Many = Reply , One =  Board 여러개 답변은 하나에 게시글에 존재할 수 있다. 
	@JoinColumn(name="boardId") // 컬럼 이름을 정하는 것
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userId") // 필드명 : userId
	private User user;

	@CreationTimestamp
	private Timestamp createDate;
	
}
