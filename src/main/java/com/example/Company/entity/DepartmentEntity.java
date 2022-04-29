package com.example.Company.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "department")
@Data
public class DepartmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "d_id")
	private int id;
	@Column(name = "d_name")
	private String name;
	@Column(name = "d_creationdate")
	private Date creationdate;
	@OneToMany(mappedBy = "departmentEntity")
	private List<Ticket> tickets;
}
