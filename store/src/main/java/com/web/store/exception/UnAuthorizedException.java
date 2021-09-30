package com.web.store.exception;

public class UnAuthorizedException extends Exception {

	private static final long serialVersionUID = 2645811140442959912L;

	String message;

	public UnAuthorizedException() {
		this.message = "Not authorized to access this resource";
	}

	public UnAuthorizedException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
