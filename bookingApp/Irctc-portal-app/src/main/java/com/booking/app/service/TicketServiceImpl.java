package com.booking.app.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.booking.app.binding.PassengerInfo;
import com.booking.app.binding.Ticket;



@Service
public class TicketServiceImpl implements TicketService {
	
	Map<Integer, Ticket> ticketMapDB = new HashMap<>();

	@Override
	public Ticket bookTicket(PassengerInfo passengerInfo) {
		Ticket ticket = new Ticket();

		Random r = new Random();
		int low = 10000;
		int high = 20000;
		Integer integer = r.nextInt(high-low) + low;
		
		ticket.setTicketNum(integer);
		BeanUtils.copyProperties(passengerInfo, ticket);
		ticket.setStatus("confirm");
		ticketMapDB.put(integer, ticket);
		return ticket;
	}

	@Override
	public Collection<Ticket> getAllTickets() {
	
		return ticketMapDB.values();
	}

}
