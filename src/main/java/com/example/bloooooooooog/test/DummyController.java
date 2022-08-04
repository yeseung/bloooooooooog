package com.example.bloooooooooog.test;

import java.util.List;


//import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

//import java.util.function.Supplier;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bloooooooooog.model.RoleType;
import com.example.bloooooooooog.model.User;
import com.example.bloooooooooog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DummyController {
	
	private final UserRepository userRepository;
	
	/*@PostConstruct
    public void init() {
        for (int i = 0; i < 2; i++) {
            User user = new User();
            user.setUsername(UUID.randomUUID().toString());
            user.setPassword(UUID.randomUUID().toString());
            user.setEmail(UUID.randomUUID().toString());
            user.setRole(RoleType.USER);
            userRepository.save(user);
            //System.out.println("board.getId() = " + user);
        }
    }*/
	
	
	
	//http://localhost:8081/blog/dummy/user/1
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "err";
		}
		return "ok~";
	}

	
	
	
	//http://localhost:8081/blog/dummy/user/1
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
		System.out.println(id);
		System.out.println(reqUser);
		
		User user = userRepository.findById(id).get();
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
		
		//userRepository.save(user);
		
		return user;
	}
	
	
	
	//http://localhost:8081/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	
	
	//http://localhost:8081/blog/dummy/user/page
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		//List<User> users = userRepository.findAll(pageable).getContent();
		
		if (pagingUser.isLast()) {
			
		}
		return pagingUser.getContent();
	}
		
	
	//http://localhost:8081/blog/dummy/id/1
	@GetMapping("/dummy/id/{id}")
	public User detail(@PathVariable("id") int id) {
		
		//user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면
		//user가 null이 될 것 아냐?
		//그럼 return null 이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		//Optional로 너의 User 객체를 감싸서 가져올테니
		//null인지 아닌지 판단해서 return해!!
		
		/*return userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자가 없습니다. id : " + id);
			}
		});*/
		
		/*return userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
				return new User();
			}
		});*/
		
		
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("--해당 사용자가 없습니다. id : " + id);
		});
		
		
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		//스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		//만약에 자바 오브젝트를 리턴하게 되면 
		//MessageConvertert Jackson 라이브러리를 호출해서
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다. 
		
		return user;

	}
	
	
	//http://localhost:8081/blog/dummy/join
	@PostMapping("/dummy/join")
	public String join(@RequestParam("username") String username,
			@RequestParam("pass") String password,
			@RequestParam("email") String email) {
		System.out.println(username + " / " + password + " / " + email);
		return "ok";
	}
	
	//http://localhost:8081/blog/dummy/join1
	@PostMapping("/dummy/join1")
	public String join1(User user) {
		System.out.println(user.getUsername() + " / " + 
					user.getPassword() + " / " + 
					user.getEmail());
		System.out.println(user);
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "ok";
	}

}
