package com.excilys.computerDatabase.web.controller.api.core;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.excilys.computerDatabase.core.exception.dao.DAOException;

@RestControllerAdvice
public class ControllerAdvice {
	
	 @ExceptionHandler(value = DAOException.class)
	 public String  error503() {
		 return "503";
		 
	 }
	
	

}
