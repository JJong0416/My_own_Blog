package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	//@AuthenticationPrincipal PrincipalDetail principal 
	@GetMapping({"","/"})
	public String index() { 
		//  prefix: /WEB-INF/views/index.jsp : suffix
		return "index";
	}
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
}
