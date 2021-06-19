package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional 
	public void 글쓰기(Board board,User user ) { // title,content
		board.setCount(0); // default로 0으로 한다.
		board.setUser(user);
		boardRepository.save(board);
	}
 
}
