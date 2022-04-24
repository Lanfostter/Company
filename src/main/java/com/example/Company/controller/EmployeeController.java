package com.example.Company.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Company.entity.EmployeeEntity;
import com.example.Company.repository.EmployeeRepository;

@Controller
@RequestMapping("/emloyee")
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/add")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new EmployeeEntity());
		return "Employee/add_employee";
	}

	@PostMapping("/add")
	public String addEmployee(@ModelAttribute("employee") EmployeeEntity employeeEntity,
			@RequestParam(name = "birthday") String birthday, @RequestParam(name = "avatar") MultipartFile avatar)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String originalFilename = avatar.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(lastIndex);

		String avatarFilename = System.currentTimeMillis() + ext;
		File newfile = new File("C:\\Users\\fostt\\eclipse-workspace\\Company\\src\\main\\resources\\static\\" + avatarFilename);
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(avatar.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		employeeEntity.setBirthday(simpleDateFormat.parse(birthday));

		return birthday;
	}
}
