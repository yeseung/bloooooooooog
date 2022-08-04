package com.example.bloooooooooog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.bloooooooooog.model.Reply;
import com.example.bloooooooooog.model.User;
import com.example.bloooooooooog.repository.UserRepository;
import com.example.bloooooooooog.service.UserService;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
class BloooooooooogApplicationTests {

	
	//private final UserRepository userRepository;
	
	/*@Test
	void contextLoads() {
	}
	
	@Test
	public void 해쉬_암호화() {
		String str = new BCryptPasswordEncoder().encode("1234");
		System.out.println("해쉬:" + str);
	}*/
	
	/*@Test
	public void test1() {
		User user = userRepository.findById(2).get();
		
		System.out.println(" + + + + user : " + user);
		
	}*/
	
	@Test
	public void 투스트링테스트() {
		Reply reply = Reply.builder()
				.id(1)
				.user(null)
				.board(null)
				.content("ㅎㅎㅎㅎㅎㅎㅎㅎ")
				.build();
		
		System.out.println(reply);
	}

}
