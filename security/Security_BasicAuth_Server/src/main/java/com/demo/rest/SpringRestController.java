package com.demo.rest;

import java.net.http.HttpHeaders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringRestController {

	@GetMapping("/")
	public String getMSg() {
		return "welcome to spring securiety";
	}
}
