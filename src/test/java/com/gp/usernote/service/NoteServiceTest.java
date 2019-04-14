package com.gp.usernote.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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
	public void getUserNotesTest(){
		when(noteRepository.getUserNotes(TEST_EMAIL)).thenReturn(Arrays.asList(note));
		List<Note> listNode = noteService.getUserNotes(TEST_EMAIL);
		assertEquals(1, listNode.size());
		assertEquals(note.getTitle(), listNode.get(0).getTitle());
		assertEquals(note.getNote(), listNode.get(0).getNote());
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
	
	@Test(expected=EntityNotFoundException.class)
	public void updateNoteEntityNotFoundTest(){
		when(noteRepository.getUserNote(note.getTitle(), TEST_EMAIL)).thenReturn(null);
		noteService.updateNote(note, TEST_EMAIL);
	}
	
	@Test
	public void updateNoteTest() {
		when(noteRepository.getUserNote(note.getTitle(), TEST_EMAIL)).thenReturn(note);
		when(noteRepository.save(note)).thenReturn(note);
		Note persistedNode = noteService.updateNote(note, TEST_EMAIL);
		assertNotNull(persistedNode);
		assertNotNull(persistedNode.getUpdateTime());
	}
	
	@Test(expected=EntityNotFoundException.class)
	public void deleteNoteEntityNotFoundTest(){
		when(noteRepository.getUserNote(note.getTitle(), TEST_EMAIL)).thenReturn(null);
		noteService.deleteNote(note, TEST_EMAIL);
	}
	
	@Test
	public void deleteNoteTest(){
		when(noteRepository.getUserNote(note.getTitle(), TEST_EMAIL)).thenReturn(note);
		noteService.deleteNote(note, TEST_EMAIL);
		verify(noteRepository, times(1)).delete(note);
	}
}
