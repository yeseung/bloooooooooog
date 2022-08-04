package com.example.bloooooooooog.test;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {
	
	private static final String TAG = "HttpController : ";
	
	
	@GetMapping("/http/lombok")
	public String lombok() {
		//Member m = new Member(1, "qqqq", "wwww", "eeee");
		Member m = Member.builder().id(777).name("sdgsdf").build();
		System.out.println(TAG + "getter : " + m.getName());
		m.setName("3456345sdfgsdfgsdf");
		System.out.println(TAG + "getter : " + m.getName());
		return "ok";
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		
		return "get 요청 " + m.getId() + " / " + 
				m.getName() + " / " + m.getPass() + " / " +
				m.getEmail();
	}
	
	@PostMapping("/http/post")
	public String postTest(Member m) {
		return "post 요청 " + m.getId() + " / " + 
				m.getName() + " / " + m.getPass() + " / " +
				m.getEmail();
	}
	
	@PostMapping("/http/post1")
	public String postTest1(@RequestBody String text) {
		return "post 요청 " + text;
	}
	
	@PostMapping("/http/post2")
	public String postTest2(@RequestBody Member m) {
		return "post 요청 " + m.getId() + " / " + 
				m.getName() + " / " + m.getPass() + " / " +
				m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" + m.getId() + " / " + 
				m.getName() + " / " + m.getPass() + " / " +
				m.getEmail();
	}
	
	@DeleteMapping("/http/del")
	public String deleteTest() {
		return "delete 요청";
	}

}
