package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
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
	
	// https://localhost:7070/blog/dummy/join(요청)
	// 이때, http의 body에 username, password, email 데이터를 가지고 (요청)
	@Autowired//(DI)
	private UserRepository userRepository;	
	
	// emal, password 수정해야해서 받아와야한다.
	//insert할때는 form태그 받아왔다면, 이번에는 Json 데이터로 받아서 테스트
	
	// save함수는 id를 전달하지 않거나, 전달했는데 데이터 없으면 insert
	// save함수는 id를 전달했는데 전달한 값이 있으면 update
	@Transactional
	@PutMapping("/dummy/user/{id}") //json 데이터 요청 -> Java Object(MessageConverter의 Jackson 라이브러리로 받아서 변환해준다. 이때 필요한 어노테이션이 RequestBody
	public User updateUser (@PathVariable Long id, @RequestBody User requestUser) {
		
		// 첫번째로는 id값을 통해서 유저를 찾아와야한다. 안그러면 모든 값들 다 불러와서 다시 저장해야하기 때문.
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정 실패");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		// 더티 체킹
		return null;
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터를 리턴할 예정
	// 페이지 시작은 0부터
	@GetMapping("/dummy/user")
	public List<User> pageList (@PageableDefault(size=2, sort="id",direction = Sort.Direction.DESC) Pageable pageable){ 
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent(); 
		return users;
	}
	
	
	//ex) http://localhost:7070/blog/dummy/user/1,2,3, ....
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
			} 
		});
		// 요청 : 웹브라우저 , user 객체 = 자바 오브젝트, 따라서 웹 브라우저가 이해할 수 있는 데이터 json으로 변환시켜야한다.
		// 근데, 스프링부트에 MessageConverter가 응답시 자동 작동이 되는데, 자바 오브젝트를 리턴하면, Jackson이라는 라이브러리를 호출해, 오브젝트를 json으로 변환 후 , 던져준다.
				return user;
	}
	
	
	
	@PostMapping("/dummy/join")
	public String join(User user) { // key = value 값을 받는다는 것 즉, 스프링이 제공해주는 규칙
		
		//@ColumnDefault("user");	
		user.setRole(RoleType.USER);
		 
		userRepository.save(user);
		return " 회원가입이 완료되었습니다."; 
		
	}
}
