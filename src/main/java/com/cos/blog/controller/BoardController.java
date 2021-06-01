package com.cos.blog.controller;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 646cf0f (스프링 시큐리티 로그인)
>>>>>>> b3bbba3 (스프링 시큐리티 로그인 구현)
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"","/"})
	public String index() {
		//  prefix: /WEB-INF/views/index.jsp : suffix
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;

@Controller
public class BoardController {
	
	//@AuthenticationPrincipal PrincipalDetail principal 
	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal ) { 
		//  prefix: /WEB-INF/views/index.jsp : suffix
		System.out.println(principal.getUsername());
>>>>>>> 722d462 (스프링 시큐리티 로그인)
>>>>>>> 646cf0f (스프링 시큐리티 로그인)
>>>>>>> b3bbba3 (스프링 시큐리티 로그인 구현)
		return "index";
	}
}
