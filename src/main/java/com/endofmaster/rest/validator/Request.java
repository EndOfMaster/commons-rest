package com.endofmaster.rest.validator;

import org.springframework.validation.Errors;

/**
 * @author ZM.Wang
 */
public interface Request {
    void validate(Errors errors);
}
