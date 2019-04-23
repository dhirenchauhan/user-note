package com.gp.usernote.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.gp.usernote.exception.ResourceNotFoundException;
import com.gp.usernote.model.Note;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseControllerTest {

	private static final String TEST_EMAIL = "test@email.com";
	
	@InjectMocks
	private BaseController baseController;
	
	@Before
	public void setUp(){
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
	}
	
		
	@Test
	public void entityCheckTest(){
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		baseController.entityCheck(new Note());
		assertTrue(true);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void entityCheckTest_Exception01() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(null);
		baseController.entityCheck(new Note());
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void entityCheckTest_Exception02() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		baseController.entityCheck(null);
	}

}
