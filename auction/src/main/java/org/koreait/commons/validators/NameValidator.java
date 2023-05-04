package org.koreait.commons.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface NameValidator {

    default boolean NameCheck(String userNm){
        String regex = "^[가-힣]{2,4}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userNm);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
}
