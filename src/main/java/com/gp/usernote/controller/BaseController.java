package com.gp.usernote.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.context.SecurityContextHolder;

import com.gp.usernote.util.RestConditionCheck;

public class BaseController {

	protected <T> void entityCheck(T resource) {
		RestConditionCheck.checkNotNull(getUserEmail(), "User not found");
		RestConditionCheck.checkNotNull(resource);
	}
	
	protected String getUserEmail() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return Strings.isNotBlank(email) ? email : null;
	}
}
