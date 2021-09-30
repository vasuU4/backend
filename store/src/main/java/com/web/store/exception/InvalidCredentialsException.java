package com.web.store.exception;

import org.springframework.stereotype.Component;

@Component
public class InvalidCredentialsException extends RuntimeException {
	private static final long serialVersionUID = -3606642119678206490L;

	String message;

	public InvalidCredentialsException() {
		message = "Invalid credentials";
	}

	@Override
	public String getMessage() {
		return message;
	}
}
