package com.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

	@GetMapping("/admin")
	public String admin() {
		return"welcome admin";
	}
	
	@GetMapping("/user")
	public String user() {
		return"welcome USER";
	}
	
	@GetMapping("/")
	public String welcome() {
		return"welcome ALL";
	}
}
