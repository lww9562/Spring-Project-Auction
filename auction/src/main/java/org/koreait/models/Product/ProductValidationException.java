package org.koreait.models.Product;

import org.koreait.commons.CommonException;
import org.springframework.http.HttpStatus;

public class ProductValidationException extends CommonException {
    private  String field;

    public ProductValidationException(String message) {
        super(message);
    }

    public ProductValidationException(String message, HttpStatus status) {
        super(message, status);
    }
    public String getField(){
        return field;
    }
}
