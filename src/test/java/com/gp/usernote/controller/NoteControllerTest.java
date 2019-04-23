package com.gp.usernote.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gp.usernote.exception.BadRequestException;
import com.gp.usernote.exception.ResourceNotFoundException;
import com.gp.usernote.model.Note;
import com.gp.usernote.service.NoteService;

@RunWith(SpringJUnit4ClassRunner.class)
public class NoteControllerTest {

	private static final String TEST_EMAIL = "test@email.com";
	
	@InjectMocks
	private NoteController noteController;
	
	@Mock
	private NoteService noteService;
	
	private Note note = null;
	
	@Before
	public void setUp(){
		Authentication authentication = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		note = new Note();
		note.setTitle("title");
		note.setNote("note");
	}

	@Test
	public void getUserNotes_Success() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		ResponseEntity<List<Note>> response = noteController.getUserNotes();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(noteService, times(1)).getUserNotes(TEST_EMAIL);
	}
	
	@Test(expected = BadRequestException.class)
	public void createNote_Exception01() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		note.setTitle(null);
		noteController.createNote(note);
	}

	@Test(expected = BadRequestException.class)
	public void createNote_Exception02() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		note.setNote(null);
		noteController.createNote(note);
	}

	@Test
	public void createNote_Success() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		noteController.createNote(note);
		verify(noteService,times(1)).createNote(note, TEST_EMAIL);
	}

	@Test(expected = BadRequestException.class)
	public void updateNote_Exception01() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		note.setTitle(null);
		noteController.updateNote(note);
	}
	
	@Test(expected = BadRequestException.class)
	public void updateNote_Exception02() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		note.setNote(null);
		noteController.updateNote(note);
	}

	@Test
	public void updateNote_Success() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		noteController.updateNote(note);
		verify(noteService,times(1)).updateNote(note, TEST_EMAIL);
	}
	
	@Test(expected = BadRequestException.class)
	public void deleteNote_Exception01() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		note.setTitle(null);
		noteController.deleteNote(note);
	}
	
	@Test
	public void deleteNote_Success() {
		when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(TEST_EMAIL);
		noteController.deleteNote(note);
		verify(noteService,times(1)).deleteNote(note, TEST_EMAIL);
	}
}
