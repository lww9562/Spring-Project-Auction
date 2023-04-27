package org.koreait.commons.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface EmailValidator {
	default boolean emailCheck(String email){
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches()){
			return true;
		}
		return false;
	}
}

