package com.management.app.service;

import java.util.List;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.app.Bindings.Quote;
import com.management.app.Bindings.QuotesApiResponse;

@Service
public class DashboardServiceImpl implements DashboardService {

	private String url = "https://type.fit/api/quotes";
	
	Quote[] quotes = null;
	
	public String getQuotes() {

		if (quotes == null) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> forEnity = restTemplate.getForEntity(url,String.class);
			String body = forEnity.getBody();
			
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				 quotes = objectMapper.readValue(body, Quote[].class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Random random = new Random();
		int num = random.nextInt(quotes.length-1);
		return quotes[num].getText();
		
	}

}
