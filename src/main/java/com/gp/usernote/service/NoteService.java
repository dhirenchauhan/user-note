package com.gp.usernote.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.usernote.model.Note;
import com.gp.usernote.model.User;
import com.gp.usernote.repository.NoteRepository;
import com.gp.usernote.util.ServiceConditionCheck;

/**
 * Class : NoteService
 * Purpose : This is service class to Support Note Service Operation
 * @author dhiren
 *
 */

@Service
public class NoteService {

	@Autowired
	private UserService userService;

	@Autowired
	private NoteRepository noteRepository;
	
	public List<Note> getUserNotes(String userEmail) {
		return noteRepository.getUserNotes(userEmail);
	}
	
	public Note createNote(Note note, String userEmail) {
		User user = userService.findUserByEmail(userEmail);
		note.setUser(user);
		return noteRepository.save(note);
	}
	
	public Note updateNote(Note note, String userEmail) {
		Note persistedNote = ServiceConditionCheck
				.checkEntityExists(noteRepository.getUserNote(note.getTitle(), userEmail), "Note entity not found");
		persistedNote.setNote(note.getNote());
		persistedNote.setUpdateTime(new Date());
		return noteRepository.save(persistedNote);
	}

	public void deleteNote(Note note, String userEmail) {
		Note persistedNote = ServiceConditionCheck
				.checkEntityExists(noteRepository.getUserNote(note.getTitle(), userEmail), "Note Entity not found");
		noteRepository.delete(persistedNote);
	}
}
