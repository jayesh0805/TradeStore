package com.db.store.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TradeExceptionalHandler {
   //Method to handle all types of Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}

}
