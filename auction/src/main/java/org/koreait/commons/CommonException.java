package org.koreait.commons;

import org.springframework.http.HttpStatus;

//모든 예외는 해당 예외를 상속받을 것
//공통 예외처리를 위함
public class CommonException extends RuntimeException{
	private HttpStatus status;

	public CommonException(String message){
		this(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public CommonException(String message, HttpStatus status){
		super(message);

		this.status = status;
	}

	public HttpStatus getStatus(){
		return status;
	}
}
