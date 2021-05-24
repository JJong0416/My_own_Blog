package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다. IOC해준다는 것
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional // 이 전체를 하나의 tx로 묶어주는 어노테이션. 실패하면 롤백이 된다.
	public int 회원가입(User user) {
		try {
			userRepository.save(user);
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("UserService : 회원가입() : " + e.getMessage());
		}
			return -1;
	}

}
