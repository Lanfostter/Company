package com.example.Company.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.example.Company.entity.IdCard;
import com.example.Company.repository.EmployeeRepository;
import com.example.Company.repository.IdCardRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	IdCardRepository cardRepository;

	@GetMapping("/add")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new EmployeeEntity());
		return "Employee/add_employee";
	}

	@PostMapping("/add")
	public String addEmployee(@ModelAttribute("employee") EmployeeEntity employeeEntity,
			@RequestParam("ebirthday") String birthday, @RequestParam("idnumber") String idnumber,
			@RequestParam(name = "img") MultipartFile avatar) throws ParseException {
		String originalFilename = avatar.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(lastIndex);

		String avatarFilename = System.currentTimeMillis() + ext;
		File newfile = new File(
				"C:\\Users\\fostt\\eclipse-workspace\\Company\\src\\main\\resources\\static\\img\\" + avatarFilename);
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
		IdCard card = new IdCard();
		card.setIdnumber(idnumber);
		card.setEmployeeEntity(employeeEntity);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		employeeEntity.setBirthday(dateFormat.parse(birthday));
		employeeEntity.setAvatar(avatarFilename);
		employeeRepository.save(employeeEntity);
		cardRepository.save(card);
		return "redirect:/employee/list";
	}

	@GetMapping("/list")
	public String listEmployee(Model model) {
		List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
		model.addAttribute("employees", employeeEntities);
		return "Employee/list_employee";
	}

	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") int id) {
		employeeRepository.deleteById(id);
		return "redirect:/employee/list";
	}

	@GetMapping(value = "/download")
	public void download(HttpServletResponse response, @RequestParam("image") String image) {
		final String uploadFolder = "C:\\Users\\fostt\\eclipse-workspace\\Company\\src\\main\\resources\\static\\img\\";// tao
																														// thu
																														// muc
																														// luu
																														// anh
		File file = new File(uploadFolder + File.separator + image);
		if (file.exists()) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
