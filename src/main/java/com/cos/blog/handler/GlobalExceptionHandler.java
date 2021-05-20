package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 exception 터졌을 때, 여기 컨트롤러에 들어온다.
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class) // IllegalArgumentException 오류 발생하면, 에러는 e에 넣는다.
	public String handleArgumentException (Exception e) {
		return "<h1>"+ e.getMessage() + "</h1>";
	}
	
	
}
