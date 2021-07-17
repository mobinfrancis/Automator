package com.automator.handlers.exceptionHandler;

public class FrameworkException extends RuntimeException {

	public FrameworkException(final String message) {
		super(message);
	}

	public FrameworkException(Throwable throwable) {
		super(throwable);
	}

	public FrameworkException(final String message, Throwable throwable) {
		super(message, throwable);
	}

}
