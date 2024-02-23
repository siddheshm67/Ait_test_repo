package com.booking.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.booking.app.binding.Passenger;
import com.booking.app.binding.ServiceClass;
import com.booking.app.binding.Ticket;

@Controller
public class TripController {
	
	@Autowired
	private ServiceClass serviceClass;
	
	@PostMapping("/ticket")
	public String bookTicket(Passenger passenger,Model model) {
		Ticket ticket = serviceClass.bookTicket(passenger);
		String msg1 = "Ticked booked Successfully ";
		String msg2 = "Ticket Status : "+ ticket.getStatus();
		String msg3 = "Ticket booking number : "+ticket.getTicketNum();
		model.addAttribute("msg1", msg1);
		model.addAttribute("msg2", msg3);
		model.addAttribute("msg3", msg2);
		return "bookTicket";
	}
	
	@GetMapping("/book-ticket")
	public String bookTicketData(Model model) {
		model.addAttribute("passenger",new Passenger());
		return "bookTicket";
	}
	
	@GetMapping("/tickets")
	public String getTicket(Model model) {
		List<Ticket> ticketList = serviceClass.getTickets();
		ticketList.stream().forEach(e->System.out.println(e));
		model.addAttribute("ticketList",ticketList);
		return "index";
	}

}
