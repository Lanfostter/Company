package com.example.Company.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Employee")
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "e_name")
	private String name;
	@Column(name = "e_gender")
	private String gender;
	@Column(name = "e_birthday")
	private Date birthday;
	@Column(name = "e_address")
	private String address;
	@Column(name = "e_avatar")
	private String avatar;
	@Column(name = "e_phone")
	private String phone;
	@OneToOne(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
	private IdCard idnumber;
	@Column(name = "e_level")
	private int level;
	@Column(name = "e_salary")
	private double salary;

}
