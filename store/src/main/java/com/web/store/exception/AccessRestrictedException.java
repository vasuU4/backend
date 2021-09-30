package com.web.store.exception;

public class AccessRestrictedException extends RuntimeException {

	private static final long serialVersionUID = 5734221695371109201L;

	String message;

	public AccessRestrictedException(String message) {
		this.message = message;
	}

	public AccessRestrictedException() {
		this.message = "Access disabled";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

