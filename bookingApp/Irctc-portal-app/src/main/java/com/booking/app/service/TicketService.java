package com.booking.app.service;

import java.util.Collection;
import java.util.List;

import com.booking.app.binding.PassengerInfo;
import com.booking.app.binding.Ticket;

public interface TicketService {
	
	public Ticket bookTicket(PassengerInfo passengerInfo);
	public Collection<Ticket> getAllTickets();

}
