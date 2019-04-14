package com.gp.usernote.exception;

/**
 * Class : BadRequestException
 * Purpose : This class is for handling Bad Request Exception
 * @author dhiren
 *
 */

public class BadRequestException extends RuntimeException {
	public BadRequestException() {
		super();
	}

	public BadRequestException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(final String message) {
		super(message);
	}

	public BadRequestException(final Throwable cause) {
		super(cause);
	}
}
