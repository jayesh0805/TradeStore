package com.db.store.utils;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TradeExceptionalHandler {

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity< List<String>> handleConstraintViolation(
	  ConstraintViolationException ex) {
	    List<String> errors = new ArrayList<String>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getRootBeanClass().getName() + " " + 
	          violation.getPropertyPath() + ": " + violation.getMessage());
	    }
	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<List<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	    List<String> errors = new ArrayList<String>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	    
	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
   //Method to handle all types of Exceptions
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> handleException(Exception ex) {
//
//		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//
//	}

}
