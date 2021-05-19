package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//DAO라고 생각하면 된다. 
// 또한 자동으로 bean 등록이 된다. 띠리서 @Repository가 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Long> { // 해당 jpa 레파지토리는 유저 테이블이 관리하는 repository. 그리고 p.k는 숫자다 

	

}
