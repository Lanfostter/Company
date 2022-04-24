package com.example.Company.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "IdCard")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IdCard {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "i_id")
	private int id;
	@OneToOne
	@Column(name = "i_number")
	private String idnumber;
}
