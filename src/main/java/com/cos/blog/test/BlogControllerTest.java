package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 특정 어노테이션이 붙어있는 클래스 파일들을 new해서(IoC) 스프링 컨테이너가 관리.
public class BlogControllerTest {
		
	@GetMapping("/test/hello")	
	public String hello() {
		return "<h1>hello spring boot </h1>";
	}
}
