package com.mohyehia.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohyehia.bookstore.entities.UserPayment;

@Repository
public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {
	List<UserPayment> findByUserId(Long userId);
}
