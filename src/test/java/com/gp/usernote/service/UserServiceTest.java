package com.gp.usernote.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gp.usernote.model.User;
import com.gp.usernote.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	private static final String TEST_EMAIL = "temp@email.com";

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void findUserByEmailTest(){
		User user = new User();
		user.setEmail(TEST_EMAIL);
		when(userRepository.findUserByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));
		assertNotNull(userService.findUserByEmail(TEST_EMAIL));
		assertEquals(TEST_EMAIL, user.getEmail());
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void findUserByEmailException() {
		when(userRepository.findUserByEmail(TEST_EMAIL)).thenReturn(null);
		userService.findUserByEmail(anyString());
	}
}
