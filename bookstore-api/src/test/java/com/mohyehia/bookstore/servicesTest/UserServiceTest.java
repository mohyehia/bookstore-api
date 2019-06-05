package com.mohyehia.bookstore.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mohyehia.bookstore.entities.ApiUser;
import com.mohyehia.bookstore.repositories.RoleRepository;
import com.mohyehia.bookstore.repositories.UserRepository;
import com.mohyehia.bookstore.services.UserService;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private RoleRepository roleRepository;
	
	@Autowired
	private UserService userService;
	
	@TestConfiguration
	static class UserServiceContextConfiguration {
		@Bean
		public UserService userService() {
			return new UserService();
		}
	}
	
	@Test
	public void whenFindAll_returnUsersList() {
		// Mockup data
		ApiUser user1 = new ApiUser("moh@mail.com", "mohamemd", "yehia", "123456", "010213544");
		ApiUser user2 = new ApiUser("ahmed@mail.com", "ahmed", "yehia", "123456", "010213544");
		
		List<ApiUser> users = Arrays.asList(user1, user2);
		
		// Given
		given(userRepository.findAll()).willReturn(users);
		
		// Assertion
		assertThat(userService.findAll())
			.hasSize(2)
			.contains(user1, user2);
	}
	
	@Test
	public void whenUserSignup_thenCreateUser() {
		// Mockup data
		ApiUser user = new ApiUser("moh@mail.com", "mohamemd", "yehia", "123456", "010213544");
		
		// Given
		given(userRepository.save(Mockito.any(ApiUser.class))).willReturn(user);
		
		ApiUser createdUser = userRepository.save(user);
		
		// Assertion
		assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());
	}
	
}
