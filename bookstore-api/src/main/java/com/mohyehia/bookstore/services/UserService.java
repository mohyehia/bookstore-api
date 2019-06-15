package com.mohyehia.bookstore.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohyehia.bookstore.entities.ApiUser;
import com.mohyehia.bookstore.entities.Role;
import com.mohyehia.bookstore.entities.UserBilling;
import com.mohyehia.bookstore.entities.UserPayment;
import com.mohyehia.bookstore.entities.UserShipping;
import com.mohyehia.bookstore.exceptions.ConflictException;
import com.mohyehia.bookstore.repositories.RoleRepository;
import com.mohyehia.bookstore.repositories.UserPaymentRepository;
import com.mohyehia.bookstore.repositories.UserRepository;
import com.mohyehia.bookstore.repositories.UserShippingRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	@Bean
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApiUser user = userRepository.findByEmail(username).get();
		if(user == null) 
			throw new UsernameNotFoundException("Username {" + username + "} not found!");
		return user;
	}
	
	public ApiUser save(ApiUser user) {
		if(exists(user.getEmail())) 
			throw new ConflictException(String.format("An existing user account with the same email address [%s] was found in database.", user.getEmail()));
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		if(user.getRoles() == null) {
			String roleName = "ROLE_USER";
			Role role = roleRepository.findByName(roleName);
			user.setRoles(new HashSet<>(Arrays.asList(role)));
		}
		return userRepository.save(user);
	}
	
	public List<ApiUser> findAll() {
		return userRepository.findAll();
	}
		
	public ApiUser updateUserBilling(ApiUser user, UserPayment payment, UserBilling billing) {
		payment.setUserId(user.getId());
		payment.setUserBilling(billing);
		payment.setDefaultPayment(true);
		billing.setUserPayment(payment);
		return userRepository.save(user);
	}
	
	public void setUserDefaultPayment(Long userId, Long userPaymentId) {
		List<UserPayment> userPayments = userPaymentRepository.findByUserId(userId);
		for (UserPayment userPayment : userPayments) {
			if(userPayment.getId() == userPaymentId)
				userPayment.setDefaultPayment(true);
			else userPayment.setDefaultPayment(false);
			userPaymentRepository.save(userPayment);
		}
	}
	
	public void setUserDefaultShipping(Long userId, int userShippingId) {
		List<UserShipping> userShippings = userShippingRepository.findByUserId(userId);
		for (UserShipping userShipping : userShippings) {
			if(userShipping.getId() == userShippingId)
				userShipping.setDefaultShipping(true);
			else userShipping.setDefaultShipping(false);
			userShippingRepository.save(userShipping);
		}
	}
	
	private boolean exists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

}
