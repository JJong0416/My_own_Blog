package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
<<<<<<< HEAD
import com.cos.blog.model.RoleType;
=======
<<<<<<< HEAD
import com.cos.blog.model.RoleType;
=======
<<<<<<< HEAD
import com.cos.blog.model.RoleType;
=======
>>>>>>> 722d462 (스프링 시큐리티 로그인)
>>>>>>> 646cf0f (스프링 시큐리티 로그인)
>>>>>>> b3bbba3 (스프링 시큐리티 로그인 구현)
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // Username, password, email
		// DB에 Insert 하고 아래에서 return이 되면 된다.
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 646cf0f (스프링 시큐리티 로그인)
>>>>>>> b3bbba3 (스프링 시큐리티 로그인 구현)
		
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);  // 반환값은 정상값인 200과 1을 반환
	}
	
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);  // 반환값은 정상값인 200과 1을 반환
	}
	// 여기에 /auth/loginProc를 안만드는 이유는 시큐리티가 loginForm에서 가로채게 만들기 위해서.
>>>>>>> 722d462 (스프링 시큐리티 로그인)
>>>>>>> 646cf0f (스프링 시큐리티 로그인)
>>>>>>> b3bbba3 (스프링 시큐리티 로그인 구현)
	
	
	/* 
	 @Autowired
	private HttpSession session;
	 
	 @PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
		 User principal =  userService.로그인(user); //principal(접근주체)
		 
		 if (principal != null) { // null이 아니면 세션을 만들어준다.
			 session.setAttribute("principal", principal);
		 }
		 
		 return new ResponseDto<Integer>(HttpStatus.OK.value(),1);  
		 // 세션을 만들고나서 1을 응답하면 loginform에서 user.js 실행될 때, login이 완료되면 /blog로가고
		 // 블로그 가게되면 header.jsp 실행후 jstl 연결
	}
	 */
	
}
