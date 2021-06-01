package com.cos.blog.controller;

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
		return "index";
	}
}
