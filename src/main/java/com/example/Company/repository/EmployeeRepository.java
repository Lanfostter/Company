package com.example.Company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Company.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{

}
