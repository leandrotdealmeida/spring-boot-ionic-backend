package com.trovilho.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();	

	public ValidationError(Long timeStamp, Integer status, String msg, String message, String path) {
		super(timeStamp, status, msg, message, path);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String mensagem) {
		errors.add(new FieldMessage(fieldName, mensagem));
	}
	
	

}
