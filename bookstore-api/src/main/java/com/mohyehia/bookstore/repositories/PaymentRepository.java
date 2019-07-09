package com.mohyehia.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohyehia.bookstore.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
