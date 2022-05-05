package com.crud.ecart.globalexceptionhandeler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandel extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<String> handelElementNotFound(ElementNotFoundException exception) {
		return new ResponseEntity<String>("Element Not Found !", HttpStatus.OK);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handelNoSuchElement(NoSuchElementException exception) {
		return new ResponseEntity<String>("Entities must not be null!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NegativeValueExpection.class)
	public ResponseEntity<String> handelNegativeValueExpection(NegativeValueExpection exception) {
		return new ResponseEntity<String>("Enter Positive Values", HttpStatus.BAD_REQUEST);
	}
}
