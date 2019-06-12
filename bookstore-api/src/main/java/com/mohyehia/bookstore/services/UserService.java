package com.mohyehia.bookstore.services;

import java.util.ArrayList;
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

import com.mohyehia.bookstore.entities.ApiUser;
import com.mohyehia.bookstore.entities.Role;
import com.mohyehia.bookstore.exceptions.ConflictException;
import com.mohyehia.bookstore.repositories.RoleRepository;
import com.mohyehia.bookstore.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
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
		user.setUserPayments(new ArrayList<>());
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
	
	private boolean exists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

}
