package com.management.app.service;

import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.app.Bindings.Quote;
import com.management.app.props.AppProps;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	AppProps appProps;
	
	Random random = new Random();

	private String url = appProps.getMessages().get("quouteURL");
	
	Quote[] quotes = null;
	
	public String getQuotes() {

		if (quotes == null) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> forEnity = restTemplate.getForEntity(url,String.class);
			String body = forEnity.getBody();
			
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				 quotes = objectMapper.readValue(body, Quote[].class);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		
		int num = 0 ;
		String text = null;
		if (Objects.nonNull(quotes)) {
			num = random.nextInt(quotes.length-1);
			text = quotes[num].getText();
		}
		
		return text;
		
	}
//method ended
}
