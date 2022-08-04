package com.example.bloooooooooog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.example.bloooooooooog.model.User;


//DAO
//자동으로 bean등록
//@Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

	//JPA Naming 전략
	//SELECT * FROM user WHERE username = ?1 AND password = ?2
	//User findByUsernameAndPassword(String username, String password);
	
	//@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery=true)
	//User login(String username, String password);
	
	//SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}
