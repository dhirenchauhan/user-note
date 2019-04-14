package com.gp.usernote.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gp.usernote.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsServiceTest {
	
	private static final String PASSWORD = "testpassword";

	private static final String EMAIL = "test@email.com";

	@InjectMocks
	private UserDetailsServiceImpl userDetailService;
	
	@Mock
	private UserService userService;
	
	@Test
	public void loadUserByUsernameTest() {
		User user = new User();
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		when(userService.findUserByEmail(EMAIL)).thenReturn(user);
		
		org.springframework.security.core.userdetails.UserDetails userDetailUser = userDetailService.loadUserByUsername(EMAIL);
		assertEquals(EMAIL, userDetailUser.getUsername());
		assertEquals(PASSWORD, userDetailUser.getPassword());
	}
	
	@Test(expected=Exception.class)
	public void loadUserByUsernameExceptionTest(){
		userDetailService.loadUserByUsername(null);
	}
}