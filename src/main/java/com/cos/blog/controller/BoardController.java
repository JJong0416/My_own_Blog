package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	
	@Autowired
	private BoardService boardService;
	
	
	//컨트롤러에서 세션을 어떻게 찾는지?
	//@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"","/"})
	public String index(Model model,@PageableDefault(size=3, sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.글목록(pageable)); // 데이터를 index로 가져가기 위해선, 스프링에서는 Model이라는 것을 지원해준다.
		return "index"; // 인덱스라는 페이지로 boards가 날라간다. 또한, Controller(rest는 다름) 는 날라갈 때, viewResolver가 작동하는데, 
		//해당 인덱스 페이지로 모델의 정보를 들고 이동한다
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		boardService.글상세보기(id);
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) { // 모델은 해당 데이터를 view까지 이동하는 것
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/updateForm";
		
	}
	
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
}
