package com.magalhaes.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	private List<FieldMessage> list = new ArrayList<>();
	
	
	
	public ValidationError(Integer status, String msg, Long timeStamp, List<FieldMessage> list) {
		super(status, msg, timeStamp);
		this.list = list;
	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName, String messagem) {
		list.add(new FieldMessage(fieldName, messagem));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
