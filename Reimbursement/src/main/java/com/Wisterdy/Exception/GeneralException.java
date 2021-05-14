package com.Wisterdy.Exception;

public class GeneralException extends Exception {

	public GeneralException() {
		super();
	}

	public GeneralException(String message) {
		super(message);
		
	}

	public GeneralException(Throwable cause) {
		super(cause);
		
	}

	public GeneralException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public GeneralException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
