package com.trainingapps.studentmanagementsystemproject.exceptions;

public class StudentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StudentNotFoundException() {

	}

	public StudentNotFoundException(String errMessage) {
		super(errMessage);
	}

	public StudentNotFoundException(String errMessage, Throwable t) {
		super(errMessage,t);
	}
}

