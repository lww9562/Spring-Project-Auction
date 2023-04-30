package org.koreait.models.Category;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class DuplicateCategoryException extends CommonException {
	public DuplicateCategoryException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}

}
