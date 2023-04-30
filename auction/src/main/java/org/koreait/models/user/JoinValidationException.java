package org.koreait.models.user;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class JoinValidationException extends CommonException {
	public JoinValidationException(String message){
		super(message, HttpStatus.BAD_REQUEST);
	}
}
