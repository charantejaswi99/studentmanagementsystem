package com.trainingapps.studentmanagementsystemproject.exceptions;

public class FacultyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FacultyException() {

	}

	public FacultyException(String errMessage) {
		super(errMessage);
	}

	public FacultyException(String errMessage, Throwable t) {
		super(errMessage,t);
	}
}
