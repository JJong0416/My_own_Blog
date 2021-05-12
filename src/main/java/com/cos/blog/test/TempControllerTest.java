package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 일반적으로 컨트롤러가 붙으면 메서드는 file을 리턴
public class TempControllerTest {
	
	//http://localhost:7070/blog/temp/home
	@GetMapping("/temp/home")
	// 그러면 기본경로 src/main/resources/static이라는 경로안에 home.html을 반환해줘야하는데 못하는 이유는
	//  statichome.html을 리턴해주기 때문이다
	// 리턴명을 /home.html로 해야한다.
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
