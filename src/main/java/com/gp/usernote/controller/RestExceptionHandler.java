package com.gp.usernote.controller;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gp.usernote.exception.BadRequestException;
import com.gp.usernote.exception.ResourceNotFoundException;
import com.gp.usernote.model.ErrorDetail;

/**
 * Class Name : RestExceptionHandler
 * Purpose : This is for handling Rest and User defined Exception
 * ControllerAdvice Annotation over on class will make sure that 
 * all exception are handled at central place
 * @author dhiren
 */

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	public RestExceptionHandler() {
		super();
	}

	@Override
	protected final ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		return handleExceptionInternal(ex, errorDetail(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST,
				request);
	}

	@Override
	protected final ResponseEntity<Object> handleHttpMessageNotWritable(final HttpMessageNotWritableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		return handleExceptionInternal(ex, errorDetail(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class, BadRequestException.class,
			EntityNotFoundException.class, ResourceNotFoundException.class, ConstraintViolationException.class })
	protected final ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
		return handleExceptionInternal(ex, errorDetail(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		return handleExceptionInternal(ex, errorDetail(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST,
				request);
	}
	
	private final ErrorDetail errorDetail(final HttpStatus httpStatus, final Exception ex) {
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
		final String detailMessage = ExceptionUtils.getRootCauseMessage(ex);
		return new ErrorDetail(httpStatus.value(), message, detailMessage);
	}
}
