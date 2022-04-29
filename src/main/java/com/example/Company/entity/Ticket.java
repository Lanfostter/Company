package com.example.Company.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_id")
	private int id;
	@Column(name = "t_phone")
	private String phone;
	@Column(name = "t_complain")
	private String complain;
	@Column(name = "t_receivedate")
	private Date receivedate;
	@Column(name = "t_replaycustomer")
	private String reply;
	@Column(name = "t_status")
	private boolean status;
	@Column(name = "t_handledate")
	private Date handledate;
	@ManyToOne
	@JoinColumn(name = "d_id")
	private DepartmentEntity departmentEntity;
}
