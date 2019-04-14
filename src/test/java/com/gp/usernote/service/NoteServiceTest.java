package com.gp.usernote.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gp.usernote.model.Note;
import com.gp.usernote.model.User;
import com.gp.usernote.repository.NoteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class NoteServiceTest {
	private static final String TEST_EMAIL = "test@email.com";

	@InjectMocks
	private NoteService noteService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private NoteRepository noteRepository;
	
	private Note note;
	
	@Before
	public void beforeTest(){
		note = new Note();
		note.setTitle("note-title");
		note.setNote("detail note");
	}
	
	@Test
	public void createNoteTest() {
		when(userService.findUserByEmail(TEST_EMAIL)).thenReturn(mock(User.class));
		when(noteRepository.save(note)).thenReturn(note);
		Note persistedNote = noteService.createNote(note, TEST_EMAIL);
		
		assertNotNull(persistedNote.getUser());
		assertEquals("note-title", persistedNote.getTitle());
		assertEquals("detail note", persistedNote.getNote());
	}
	
}
