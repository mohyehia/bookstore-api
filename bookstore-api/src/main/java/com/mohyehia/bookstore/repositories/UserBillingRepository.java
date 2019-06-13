package com.mohyehia.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohyehia.bookstore.entities.UserBilling;

public interface UserBillingRepository extends JpaRepository<UserBilling, Long> {
	
}
