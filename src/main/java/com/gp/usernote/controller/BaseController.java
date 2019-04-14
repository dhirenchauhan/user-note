package com.gp.usernote.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.context.SecurityContextHolder;

import com.gp.usernote.util.RestConditionCheck;

/**
 * Class Name : BaseController
 * Purpose : All Controller class will extend base class.
 * This class PreCondition Rest on input before resource can server user
 * @author dhiren
 *
 */

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
