package com.mohyehia.bookstore.controllersTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohyehia.bookstore.BookstoreApiApplication;
import com.mohyehia.bookstore.entities.ApiUser;
import com.mohyehia.bookstore.entities.Role;
import com.mohyehia.bookstore.repositories.RoleRepository;
import com.mohyehia.bookstore.services.UserService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BookstoreApiApplication.class)
public class AuthControllerTest {
	
	private final String SIGNUP_URL = "/api/v1/auth/signup";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private RoleRepository roleRepository;
	
	@Test
	public void testSignup() throws Exception{
		//Mockup data
		ApiUser user = new ApiUser("moh@mail.com", "mohamemd", "yehia", "123456", "010213544");
		Role role = new Role("ROLE_USER");
		user.setRoles(new HashSet<>(Arrays.asList(role)));
		
		//Given
		given(roleRepository.findByName(anyString())).willReturn(role);
		given(userService.save(Mockito.any(ApiUser.class))).willReturn(user);
		
		mockMvc.perform(
				post(SIGNUP_URL).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(user)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstName", equalTo(user.getFirstName())));
	}
}
