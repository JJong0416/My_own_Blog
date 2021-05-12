package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// ORM이란건, 모든 언어들 Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
@Data // getter,setter
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 전체생정자
@Builder // 빌더 패턴

public class User {
 
	@Id //P.K
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 프로젝트 연결된 db의 넘버링 전략을 따라간다.
	private Long id; // 시컨스, auto_increment
	
	@Column(nullable = false, length = 30) // null이 불가능하고, 길이제한
	private String username; 
	
	@Column(nullable = false, length = 100) // 크게 준 이유는 비밀번호 암호화해서 넣을거라서.
	private String password;
	
	@Column(nullable = false, length = 50) // 크게 준 이유는 비밀번호 암호화해서 넣을거라서.	
	private String email;
	
	@ColumnDefault("'user '")
	private String role; // Enum을 쓸 것이다.
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
	
}
