package com.example.bloooooooooog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

	//http://localhost:8081/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome)(");
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String img() {
		System.out.println("img)(");
		return "/20220616_202033_1655378433.png";
	}
	
	@GetMapping("/temp/jsp")
	public String jsp() {
		System.out.println("jsp)(");
		return "home";
	}
}
