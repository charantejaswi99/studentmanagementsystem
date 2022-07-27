package com.trainingapps.studentmanagementsystemproject.exceptions;

public class AttendenceException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AttendenceException() {

	}

	public AttendenceException(String errMessage) {
		super(errMessage);
	}

	public AttendenceException(String errMessage, Throwable t) {
		super(errMessage,t);
	}
}
