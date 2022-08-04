package com.example.bloooooooooog.service;



import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bloooooooooog.dto.ResponseDto;
import com.example.bloooooooooog.model.RoleType;
import com.example.bloooooooooog.model.User;
import com.example.bloooooooooog.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	private final BCryptPasswordEncoder encoder;
	
	
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		
		//User user = userRepository.findByUsername(username).get();
		System.out.println("찾기-----------------");
		return user;
	}
	
	@Transactional
	public void joinForm(User user) {
		/*try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService 회원가입() " + e.getMessage());
		}
		return -1;*/
		
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
	}

	/*@Transactional
	public void 회원수정(User user) {
		User persistance = userRepository.findById(user.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원 찾기 실패 : " + user.getId());
				});
			
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		//종료시 = 서비스 종료 = 트랜잭션 종료 = db commit 자동
	}*/
	
	@Transactional
	public boolean 회원수정(User user, String username) {
		User persistance = userRepository.findById(user.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원 찾기 실패 : " + user.getId());
				});
		System.out.println("____________" + persistance.getUsername() +  "___" + username);
		if (persistance.getUsername().toString().equals(username))
		{	
			System.out.println("+++" + persistance.getUsername() +  "+++" + username);
			
			
			//Validate 체크
			if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
				String rawPassword = user.getPassword();
				String encPassword = encoder.encode(rawPassword);
				persistance.setPassword(encPassword);
				
				persistance.setEmail(user.getEmail());
			}
			


			
			return true;
			
		} else {
			System.out.println("---" + persistance.getUsername() +  "---" + username);
			return false;
		}
		
		

		
	}

	/*@Transactional(readOnly=true) //select할때 트랜잭션 시작, 종료시에 트랜잭션 종료(정합성)
	public User loginForm(User user) {
		return userRepository.login(user.getUsername(), user.getPassword());
		//return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}*/
	
}
