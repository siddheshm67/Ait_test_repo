package com.demo.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SpringRestController {

	@GetMapping("/")
	public String getMsgFromServer() {

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> httpEntity = new HttpEntity<>(getHeaders());
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/", HttpMethod.GET, httpEntity, String.class);
		return "servern response : " +  responseEntity.getBody();
	}

	private org.springframework.http.HttpHeaders getHeaders() {
		String adminuserCredentials = "admin:admin";
		String encodedCredentials = new String(Base64.encodeBase64(adminuserCredentials.getBytes(), false));

		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Authorization", "Basic " + encodedCredentials);

		return headers;
	}
}
