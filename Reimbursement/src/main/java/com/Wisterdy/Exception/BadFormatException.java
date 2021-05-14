package com.Wisterdy.Exception;

public class BadFormatException extends Exception {

	

	public BadFormatException() {
		super();
	}

	public BadFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public BadFormatException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BadFormatException(String message) {
		super(message);
		
	}

	public BadFormatException(Throwable cause) {
		super(cause);
		
	}

	
}
