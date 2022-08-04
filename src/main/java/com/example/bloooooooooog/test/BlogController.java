package com.example.bloooooooooog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

	@GetMapping("/test")
	public String hello() {
		return "<h1>후후후</h1>";
	}
}
