package com.example.Company.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Company.entity.DepartmentEntity;
import com.example.Company.repository.DepartmentRepository;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	DepartmentRepository departmentRepository;

	@GetMapping("/add")
	public String addDepartment(Model model) {
		model.addAttribute("department", new DepartmentEntity());
		return "Department/add_department";
	}

	@PostMapping("/add")
	public String addDepartment(@ModelAttribute("department") DepartmentEntity departmentEntity) {
		departmentEntity.setCreationdate(new Date());
		departmentRepository.save(departmentEntity);
		return "redirect:/department/list";
	}

	@GetMapping("/list")
	public String listDepartment(Model model) {
		model.addAttribute("departments", departmentRepository.findAll());
		return "Department/list_department";
	}

	@GetMapping("/delete")
	public String deleteDepartment(@RequestParam("id") int id) {
		departmentRepository.deleteById(id);
		return "redirect:/department/list";
	}
}
