package com.gp.usernote.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/notes")
public class NoteController extends BaseController {

	@GetMapping
	public String userAuthenticationTest() {
		return "User Email Tested Successfully : " + getUserEmail();
	}
}
