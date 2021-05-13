package com.cos.blog.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {
	
	@PostMapping("/dummy/join")
	public String join(@RequestParam("username") String username, String password, String email) {
		
		return " 회원가입이 완료되었습니다."; 
		
	}
}
