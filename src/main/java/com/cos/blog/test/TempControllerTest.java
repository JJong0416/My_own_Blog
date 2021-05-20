package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	@GetMapping("/temp/home")
	public String tempHome() {
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")  // jsp는 정적파일이라 톰캣이 해당 파일을 컴파일해서 html 파일로 아파치한테 던져준다.
	public String tempJsp() {
		//prefix : /WEB-INF/views/ 
	   // suffix: .jsp
	  // 풀네임 : /WEB-INF/views//test.jsp.jsp 이가 된다. 그렇기에 위에 예시처럼 /test.jsp 로 하면안된다.
		return "test";
	}
}
