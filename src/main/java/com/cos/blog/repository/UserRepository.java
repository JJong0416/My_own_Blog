package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO라고 생각하면 된다. 
// 또한 자동으로 bean 등록이 된다. 띠리서 @Repository가 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Long> { // 해당 jpa 레파지토리는 유저 테이블이 관리하는 repository. 그리고 p.k는 숫자다 

}
//JPA Naming 쿼리
// JPA에는 findByUsernameAndPassword 라는 함수가 없는데
// 이름을 이렇게 하면, 함수가 SELECT * FROM user WHERE username = ? and password = ?; 라는 쿼리가 동작해 신기하지?
// 그리고 ?에 파라미터가 하나씩 다 들어가서  SELECT * FROM user WHERE username = username and password = password; 
//User findByUsernameAndPassword(String username, String password);
 // 이렇게 해도 된다.
// 밑에와 같이 하면, login 함수가 실행되면 쿼리문이 실행되고, 리턴을 User로 해준다.
//	@Query(value="SELECT * FROM user WHERE username = ?1 and password = ?2", nativeQuery = true)
//	User login(String username, String password);