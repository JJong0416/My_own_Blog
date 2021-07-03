package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired
	private UserRepository userRepository;	
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e){
			return "삭제에 실패했습니다. 해당 ID는 DB에 없습니다.";
		}
		return "삭제되었습니다. id:" + id;
	}

	@PutMapping("/dummy/user/{id}")
	public User updateUser (@PathVariable int id, @RequestBody User requestUser) {
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});

		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		return user;
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}

	@GetMapping("/dummy/user")
	public Page<User> pageList (@PageableDefault(size=2, sort="id",direction = Sort.Direction.DESC) Pageable pageable){ 
		Page<User> pagingUser = userRepository.findAll(pageable);
		return pagingUser;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다.");
			} 
		});
				return user;
	}
	
	
	
	@PostMapping("/dummy/join")
	public String join(User user) {

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return " 회원가입이 완료되었습니다."; 
		
	}
}
