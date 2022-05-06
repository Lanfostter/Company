package com.example.Company.controller;

import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Company.entity.DepartmentEntity;
import com.example.Company.entity.Ticket;
import com.example.Company.repository.DepartmentRepository;
import com.example.Company.repository.TicketRepository;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	private static Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	DepartmentRepository departmentRepository;

	@GetMapping("/list")
	public String listTicket(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		request.getSession().setAttribute("ticketlist", null);
		if (model.asMap().get("success") != null) {
			redirectAttributes.addFlashAttribute("success", model.asMap().get("success").toString());
		}
		return "redirect:/ticket/page/1";
	}

	@GetMapping("/page/{page}")
	public String showTicketPage(HttpServletRequest request, @PathVariable int page, Model model) {
		PagedListHolder<?> pagedListHolder = (PagedListHolder<?>) request.getSession().getAttribute("ticketlist");
		int pagesize = 3;
		List<Ticket> list = (List<Ticket>) ticketRepository.findAll();
		if (pagedListHolder == null) {
			pagedListHolder = new PagedListHolder<>(list);
			pagedListHolder.setPageSize(pagesize);
		} else {
			final int goTopage = page - 1;
			if (goTopage <= pagedListHolder.getPageCount() && goTopage >= 0) {
				pagedListHolder.setPage(goTopage);
			}
		}
		request.getSession().setAttribute("ticketlist", pagedListHolder);
		int current = pagedListHolder.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 5, pagedListHolder.getPageCount());
		int totalPageCount = pagedListHolder.getPageCount();
		model.addAttribute("currentIndex", current);
		model.addAttribute("tickets", pagedListHolder);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("departments", departmentRepository.findAll());
		return "Ticket/list_ticket";
	}

	@GetMapping("/add")
	public String addTicket(Model model) {
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("departments", departmentRepository.findAll());
		return "Ticket/add_ticket";
	}

	@PostMapping("/add")
	public String addTicket(@ModelAttribute("ticket") Ticket ticket,
			@ModelAttribute("departments") DepartmentEntity departmentEntity) throws ParseException {
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
	public String searchTicket(Model model, @RequestParam("phone") String phone, @RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		if (size == null)
			size = 3;// max records per page
		if (page == null)
			page = 0;// trang hien tai

		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
		model.addAttribute("tickets", ticketRepository.searchByPhone(phone));
		return "redirect:/ticket/list";

	}
}
