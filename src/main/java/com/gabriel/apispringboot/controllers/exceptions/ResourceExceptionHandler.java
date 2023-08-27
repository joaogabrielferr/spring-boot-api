package com.gabriel.apispringboot.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gabriel.apispringboot.services.exceptions.DatabaseException;
import com.gabriel.apispringboot.services.exceptions.EntityAlreadyExistsException;
import com.gabriel.apispringboot.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

//this annotation makes this class intercept the exceptions so this object can handle them
@ControllerAdvice
public class ResourceExceptionHandler {

	
	//this method intercepts any exception of type ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,HttpServletRequest request)
	{
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError standardError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
			
	
		return ResponseEntity.status(status).body(standardError);
		
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e,HttpServletRequest request)
	{
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError standardError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
			
	
		return ResponseEntity.status(status).body(standardError);
		
	}
	
	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<StandardError> entityAlreadyExists(EntityAlreadyExistsException e, HttpServletRequest request)
	{
		String error = "Entity already exists";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError standardError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(status).body(standardError);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> fieldIsRequired(MethodArgumentNotValidException e, HttpServletRequest request)
	{
		String error = "Required fields missing";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError standardError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(status).body(standardError);
		
	}

	
}
