package org.koreait.commons.validators;

public interface MobileValidator {
	default boolean mobileCheck(String mobile){
		mobile = mobile.replaceAll("\\D", "");

		String pattern = "^01[016]\\d{3,4}\\d{4}$";

		return mobile.matches(pattern);
	}
}
