package com.trainingapps.studentmanagementsystemproject.exceptions;

public class PerformaneException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PerformaneException() {

	}

	public PerformaneException(String errMessage) {
		super(errMessage);
	}

	public PerformaneException(String errMessage, Throwable t) {
		super(errMessage,t);
	}
}
