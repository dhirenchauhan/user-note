package com.gp.usernote.exception;

/**
 * Class Name : ResourceNotFoundException
 * Purpose : This class is for handling Resource Exception
 * @author dhiren
 *
 */

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

	public ResourceNotFoundException(final Throwable cause) {
		super(cause);
	}
}
