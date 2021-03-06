package com.mohyehia.bookstore.utils;

import java.util.Arrays;
import java.util.HashSet;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mohyehia.bookstore.entities.ApiUser;
import com.mohyehia.bookstore.entities.Role;
import com.mohyehia.bookstore.repositories.RoleRepository;
import com.mohyehia.bookstore.services.UserService;

@Component
public class FirstTimeInitializer implements CommandLineRunner{

	private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// If no users exist, create new user with admin role
		if(userService.findAll().isEmpty()) {
			logger.info("No user accounts found. Creating some users...");
			ApiUser user = new ApiUser("moh@mail.com", "mohammed", "yehia", "0000", "0106006512");
			Role role = roleRepository.findByName("ROLE_ADMIN");
			user.setRoles(new HashSet<>(Arrays.asList(role)));
			userService.save(user);
		} else
			logger.info("An existing user accounts found. Skip creating new user");
	}

}
