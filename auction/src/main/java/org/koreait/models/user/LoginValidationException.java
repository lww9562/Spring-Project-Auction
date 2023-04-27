package org.koreait.models.user;

import org.koreait.commons.CommonException;

public class LoginValidationException extends CommonException {
	private String field;

	public LoginValidationException(String message) {
		super(message);
	}

	public LoginValidationException(String message, String field){
		this(message);
		this.field = field;
	}

	public String getField(){
		return field;
	}
}
