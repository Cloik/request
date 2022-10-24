package com.br.challenge.exception;

public class EntityExistingException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntityExistingException(String msg) {
		super(msg);
	}

}
