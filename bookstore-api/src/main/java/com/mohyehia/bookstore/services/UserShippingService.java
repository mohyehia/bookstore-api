package com.mohyehia.bookstore.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohyehia.bookstore.entities.UserShipping;
import com.mohyehia.bookstore.exceptions.NotFoundException;
import com.mohyehia.bookstore.repositories.UserShippingRepository;

@Service
public class UserShippingService {
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	public List<UserShipping> findByUserId(Long userId){
		return userShippingRepository.findByUserId(userId);
	}
	
	public UserShipping findById(int id) {
		try {
			return userShippingRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new NotFoundException(String.format("No such record with id [%d] was found in database.", id));
		}
	}
	
	public UserShipping save(UserShipping userShipping) {
		return userShippingRepository.save(userShipping);
	}
	
	public void deleteById(int id) {
		try {
			userShippingRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new NotFoundException(String.format("No such record with id [%d] was found in database.", id));
		}
	}
}
