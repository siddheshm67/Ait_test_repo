package com.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class DemoRestController {

	@GetMapping("/")
	@CircuitBreaker(name = "scbrakrr", fallbackMethod = "fallbackDbLogic")
	public String getMsgFromRedis() {
		System.out.println("redis method called");
		int i = 10/0;
		return "msg from redis";
	}
	
	

	public String fallbackDbLogic(Throwable t) {
		System.out.println("DB method called");
		return "msg from main db";
	}
}
