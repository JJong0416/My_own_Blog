  
package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// if  사용자가 요청 -> 응답(HTML 파일) 로 하고 싶을떄는
// @Controller로 한다.

@RestController // 사용자 요청 -> 응답(Data) 즉, 문자열 그대로 리턴 vs File로 리턴 차이다.
public class HttpControllerTest {
	
	// 인터넷 브라우저는 get 요청밖에 할 수 없다.
	// http://localhost:7070/http/get
	@GetMapping("/http/get")
	
	// 1. 쿼리 파라미터 하나하나 받는 법 @RequestParam int id, @RequestParam String username
	// 2. 전체를 받는 방법은 객체를 파라미터로 넣으면 스스로 매핑시켜준다
	public String getTest(Member m) {
		return "get 요청" + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
	}
	

	@PostMapping("/http/post") // text/plain ,  application json 보내는 방식 차이를 잘 알아야한다.
	public String postTest(@RequestBody Member m) { // MessageConverter(Spring Boot)
		return "post 요청"  + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
	}
	

	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete  요청";
	}
	
}