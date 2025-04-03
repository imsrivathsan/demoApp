package com.demoApp.demoApp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demoApp.demoApp.Utils.ServiceException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(ServiceException.class)
	    public ResponseEntity<String> handleServiceException(ServiceException ex) {
	        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleGeneralException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Something went wrong: " + ex.getMessage());
	    }

}
