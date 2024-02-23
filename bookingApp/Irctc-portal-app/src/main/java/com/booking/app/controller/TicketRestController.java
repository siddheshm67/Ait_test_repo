package com.booking.app.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.binding.PassengerInfo;
import com.booking.app.binding.Ticket;
import com.booking.app.service.TicketService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class TicketRestController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/ticket")
	public ResponseEntity<Ticket> bookTicket(@org.springframework.web.bind.annotation.RequestBody PassengerInfo passengerInfo){
		Ticket ticket = ticketService.bookTicket(passengerInfo);
		return new ResponseEntity<Ticket>(ticket,HttpStatus.CREATED);
	}
	
	@GetMapping("/tickets")
	public ResponseEntity<Collection<Ticket>> getTickets(){
		return new ResponseEntity<Collection<Ticket>>(ticketService.getAllTickets(),HttpStatus.OK);
	}

}
