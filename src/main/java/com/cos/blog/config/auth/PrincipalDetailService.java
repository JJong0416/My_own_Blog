package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service  // 빈등록해줘야한다.
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데,
	// password 부분 처리는 시큐리티가 알아서 함
	// 우리가 해야할거는 username이 DB에 있는지만 확인해주면 됨.
	// 확인을 밑에 함수에서 한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username).orElseThrow(()->{
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:" + username); 
		});
			;
		return new PrincipalDetail(principal); // 시큐리티의 세션에 유저 정보가 저장이 된다.
	}
	
}
