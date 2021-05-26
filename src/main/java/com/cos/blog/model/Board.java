package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
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
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board , One = User , 즉 한명이 여러개의 게시글 작성 O, But, 하나의 게시글이 여러명한테 쓰여질 수 x
	@JoinColumn(name="userId") 
	private User user; // DB는 오브젝트를 저장할 수 없다. 그래서 F.K 써야하는데, 자바는 오브젝트를 저장할 수 있다. 자바 프로그램에서 DB 자료형에 맞춰서 만든다.
									// 그래서 JoinColumn을 해서 user라는 오브젝트가 아닌 userid라는 값으로 넣는 것이다.
																	// mappedBy는 Reply 테이블에 Board "board"를 적은 것.
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER) // mappedBy가 적혀있으면 연관관계의 주인이 아니다. 즉, 난 F.K가 아니다. / 그러니 DB에 컬럼을 만들지 마세요 /주인은 Reply 테이블에 Board
	private List<Reply> reply; // Board를 가져오게 된면, 게시글 밑에 수많은 댓글들도 JPA가 가져오게 만드는게 좋다. 그래서 reply 객체까지 가져오게 만들고, 댓글이 한두개가 아니므로 List로 가져온다.
	
	@CreationTimestamp
	private Timestamp createDate;
}
