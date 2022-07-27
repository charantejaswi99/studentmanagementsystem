package com.trainingapps.studentmanagementsystemproject.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralisedExceptionHandler {
	
	private static final Logger LOG=LoggerFactory.getLogger(CentralisedExceptionHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(StudentNotFoundException.class)
	public String handleCarNotFound(StudentNotFoundException e) {
		String msg=e.getMessage();
		LOG.info(msg,e);
		return msg;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    public String handleInvalid(Exception e) {
        String msg = e.getMessage();
    	LOG.info(msg,e);
    	return msg;
    }
}