package com.example.Company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Company.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer>{

}
