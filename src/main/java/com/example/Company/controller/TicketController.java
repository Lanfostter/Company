package com.example.Company.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Company.entity.Ticket;
import com.example.Company.repository.TicketRepository;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	TicketRepository ticketRepository;

	@GetMapping("/list")
	public String listTicket(Model model) {
		List<Ticket> tickets = ticketRepository.findAll();
		model.addAttribute("tickets", tickets);
		return "Ticket/list_ticket";
	}

	@GetMapping("/add")
	public String addTicket(Model model) {
		model.addAttribute("ticket", new Ticket());
		return "Ticket/add_ticket";
	}

	@PostMapping("/add")
	public String addTicket(@ModelAttribute("ticket") Ticket ticket) throws ParseException {
		ticket.setReceivedate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ticket.getReceivedate());
		calendar.add(Calendar.DATE, 10);
		ticket.setHandledate(calendar.getTime());
		ticketRepository.save(ticket);
		return "redirect:/ticket/list";
	}

	@GetMapping("/delete")
	public String deleteTicket(@RequestParam("id") int id) {
		ticketRepository.deleteById(id);
		return "redirect:/ticket/list";
	}

	String date;

	@GetMapping("/update")
	public String updateTicket(Model model, @RequestParam("id") int id) {
		model.addAttribute("newticket", ticketRepository.findById(id).get());
		return "Ticket/update_ticket";
	}

	@PostMapping("/update")
	public String updateTicket(@ModelAttribute("newticket") Ticket ticket) {
		ticket.setHandledate(new Date());
		ticketRepository.save(ticket);
		return "redirect:/ticket/list";

	}

	@GetMapping("/search")
	public String searchTicket(Model model, @RequestParam("phone") String phone) {
		model.addAttribute("tickets", ticketRepository.searchByPhone(phone));
		return "redirect:/ticket/list";

	}
}
