package com.magalhaes.cursomc.services.exceptions;

public class DateIntegrityViolationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DateIntegrityViolationException(String msg) {
		super(msg);
	}
	
	public DateIntegrityViolationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
