package com.gp.usernote.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gp.usernote.model.Note;
import com.gp.usernote.service.NoteService;
import com.gp.usernote.util.RestConditionCheck;

@RestController("/notes")
public class NoteController extends BaseController {

	Logger LOG = LoggerFactory.getLogger(NoteController.class);
	
	@Autowired
	private NoteService noteService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createNote(@RequestBody Note note) {
		entityCheck(note);
		RestConditionCheck.checkRequestElementNotNull(note.getTitle(), "Note Title Required");
		RestConditionCheck.checkRequestElementNotNull(note.getNote(), "Note required");
		LOG.info("Create Note : User - {} with note title - {} ", getUserEmail(), note.getTitle());
		noteService.createNote(note, getUserEmail());
	}
}
