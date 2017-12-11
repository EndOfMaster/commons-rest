package com.jifenke.rest.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author ZM.Wang
 */
public class RequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Request.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Request request = (Request) target;
        request.validate(errors);
    }
}
