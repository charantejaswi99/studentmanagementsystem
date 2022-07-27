package com.trainingapps.studentmanagementsystemproject.exceptions;

public class StudentFeeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StudentFeeException() {

	}

	public StudentFeeException(String errMessage) {
		super(errMessage);
	}

	public StudentFeeException(String errMessage, Throwable t) {
		super(errMessage,t);
	}

}
