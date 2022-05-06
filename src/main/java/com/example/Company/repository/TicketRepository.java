package com.example.Company.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Company.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	@Query("FROM Ticket t WHERE t.phone = :phone")
	Ticket searchByPhone(@Param("phone") String phone);
	

}
