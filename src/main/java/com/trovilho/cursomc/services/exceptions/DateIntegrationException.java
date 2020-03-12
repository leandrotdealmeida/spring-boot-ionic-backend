package com.trovilho.cursomc.services.exceptions;

public class DateIntegrationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DateIntegrationException(String message, Throwable cause) {
		super(message, cause);

	}

	public DateIntegrationException(String message) {
		super(message);

	}

}
