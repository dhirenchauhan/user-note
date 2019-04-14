package com.gp.usernote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.usernote.model.Note;
import com.gp.usernote.model.User;
import com.gp.usernote.repository.NoteRepository;

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
}
