package com.excilys.computerDatabase.web.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.excilys.computerDatabase.core.exception.dao.DAOException;

@RestControllerAdvice
public class ControllerAdvice {
	
	 @ExceptionHandler(value = DAOException.class)
	 public String  error503() {
		 return "503";
		 
	 }
	
	

}
