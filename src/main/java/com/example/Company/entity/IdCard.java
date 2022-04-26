package com.example.Company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "IdCard")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IdCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "i_id")
	private int id;
	@Column(name = "i_number")
	private String idnumber;
	@OneToOne
	@JoinColumn(name = "e_id")
	private EmployeeEntity employeeEntity;
}
