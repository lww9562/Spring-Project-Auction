package org.koreait.commons.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IdValidator {

    default boolean idCheck(String userId){
        String regex = "^[a-z]+[a-z0-9]{5,19}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userId);
        if(matcher.matches()){
            return true;
        }
        return false;
    }

}
