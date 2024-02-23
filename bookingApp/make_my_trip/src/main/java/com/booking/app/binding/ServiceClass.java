package com.booking.app.binding;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceClass {
	
	public Ticket bookTicket(Passenger passenger) {
		
		String urlString = "http://54.197.28.246:8080/ticket";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ticket> responseEntity = restTemplate.postForEntity(urlString, passenger, Ticket.class);
		return responseEntity.getBody();
	}
	
	public List<Ticket> getTickets(){
		String urlString = "http://54.197.28.246:8080/tickets";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ticket[]> responseEntity = restTemplate.getForEntity(urlString, Ticket[].class);
		
		return Arrays.asList(responseEntity.getBody());
	}

}
