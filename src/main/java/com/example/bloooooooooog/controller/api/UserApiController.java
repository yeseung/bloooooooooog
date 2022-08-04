package com.example.bloooooooooog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bloooooooooog.config.auth.PrincipalDetail;
import com.example.bloooooooooog.dto.ResponseDto;
//import com.example.bloooooooooog.model.RoleType;
import com.example.bloooooooooog.model.User;
//import com.example.bloooooooooog.repository.UserRepository;
import com.example.bloooooooooog.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {
	
	private final UserService userService;
	

	private final AuthenticationManager authenticationManager;
	
	//private final HttpSession session;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("_________________ UserApiController : sava()");
		
		//user.setRole(RoleType.USER);
		//int result = userService.joinForm(user);
		userService.joinForm(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	/*@PostMapping("/api/user")
	public ResponseEntity<?> save(@RequestBody User user) {
		System.out.println("UserApiController : sava()");
		return new ResponseEntity<>("333333333", HttpStatus.OK);
	}*/
	
	
	/*@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
		System.out.println("UserApiController : login()");
	
		User principal = userService.loginForm(user); //principal (접근주체)
		if (principal != null) {
			session.setAttribute("principal", principal);
		}
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}*/
	
	
	
	@PutMapping("/user")
	public ResponseDto<?> update(@RequestBody User user,
			@AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("_________________ UserApiController : update()");
		
		/*Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);*/
		
		
		
		
		if (userService.회원수정(user, principal.getUser().getUsername())) {
			
			
			
			System.out.println(user);
			
			//세션등록
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		} else {
			return new ResponseDto<Integer>(HttpStatus.INTERNAL_SERVER_ERROR.value(), -1);
		}
		
		
	}
	
	
	
	
	
	

}
