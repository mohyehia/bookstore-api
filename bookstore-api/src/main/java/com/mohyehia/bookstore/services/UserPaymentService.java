package com.mohyehia.bookstore.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.bookstore.entities.UserPayment;
import com.mohyehia.bookstore.exceptions.NotFoundException;
import com.mohyehia.bookstore.repositories.UserPaymentRepository;

@Service
public class UserPaymentService {
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	public UserPayment findById(Long id) {
		try {
			return userPaymentRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new NotFoundException(String.format("No such record with id [%d] was found in database.", id));
		}
	}
	
	public void deleteById(Long id) {
		try {
			userPaymentRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new NotFoundException(String.format("No such record with id [%d] was found in database to be deleted.", id));
		}
	}
}
