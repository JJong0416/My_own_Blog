package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // Username, password, email
		// DB에 Insert 하고 아래에서 return이 되면 된다.
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);  // 반환값은 정상값인 200과 1을 반환
	}
	// 여기에 /auth/loginProc를 안만드는 이유는 시큐리티가 loginForm에서 가로채게 만들기 위해서.
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){ // RequestBody가 없으면 Json 못받음. 없으면 Key-value 형태로만 받고  x-www-form-urlencoded
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되었기 때문에, DB값은 변경되었지만,
		// 하지만 세션값은 변경되지 않은 상태이기 때문에, 직접 세션값을 변경해줘야 한다.
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
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
