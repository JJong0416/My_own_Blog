package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
<<<<<<< HEAD
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
<<<<<<< HEAD
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

=======
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
>>>>>>> 722d462 (스프링 시큐리티 로그인)
>>>>>>> 646cf0f (스프링 시큐리티 로그인)
>>>>>>> b3bbba3 (스프링 시큐리티 로그인 구현)
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다. IOC해준다는 것
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
<<<<<<< HEAD
	@Transactional // 이 전체를 하나의 tx로 묶어주는 어노테이션. 실패하면 롤백이 된다.
	public void 회원가입(User user) {
=======
<<<<<<< HEAD
	@Transactional // 이 전체를 하나의 tx로 묶어주는 어노테이션. 실패하면 롤백이 된다.
	public void 회원가입(User user) {
=======
<<<<<<< HEAD
	@Transactional // 이 전체를 하나의 tx로 묶어주는 어노테이션. 실패하면 롤백이 된다.
	public void 회원가입(User user) {
=======
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional // 이 전체를 하나의 tx로 묶어주는 어노테이션. 실패하면 롤백이 된다.
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬화
		user.setPassword(encPassword); // 비밀번호 해쉬화 해서 넣기
		user.setRole(RoleType.USER);
>>>>>>> 722d462 (스프링 시큐리티 로그인)
>>>>>>> 646cf0f (스프링 시큐리티 로그인)
>>>>>>> b3bbba3 (스프링 시큐리티 로그인 구현)
		userRepository.save(user);
	}
	

	
//	@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(이때까지 정합성 유지)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
}
