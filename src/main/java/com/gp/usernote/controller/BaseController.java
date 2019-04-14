package com.gp.usernote.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

	protected String getUserEmail() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return Strings.isNotBlank(email) ? email : null;
	}
}
