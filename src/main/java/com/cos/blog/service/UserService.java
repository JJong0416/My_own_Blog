package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다. IOC해준다는 것
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional // 이 전체를 하나의 tx로 묶어주는 어노테이션. 실패하면 롤백이 된다.
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬화
		user.setPassword(encPassword); // 비밀번호 해쉬화 해서 넣기
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional
	public void 회원수정(User user) { // 이 유저는 웹으로부터 받은 유저 데이터
		// 수정시에는 Jpa 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화 된 오브젝트를 수정하는 방식으로 진행한다.
		// Select를 먼저 하는 이유는 User오브젝트를 DB로부터 영속성 컨텍스트에 옮기기 위해서
		// 더티체킹을 이용한 회원수정 방식으로 진행
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		}); // 만약 찾았으면, persistance 변수에 오브젝트가 들어왔을 것이다.
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword); // 해쉬화
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		// 회원 수정 함수 종료 시 = 서비스 종료 = 트랜잭션 종료 = 자동 커밋(더티체킹) -> update문 날려준다.
	}
	
	
//	@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(이때까지 정합성 유지)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
}
