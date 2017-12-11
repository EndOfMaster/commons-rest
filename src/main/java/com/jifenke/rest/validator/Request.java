package com.jifenke.rest.validator;

import org.springframework.validation.Errors;

/**
 * @author ZM.Wang
 */
public interface Request {
    void validate(Errors errors);
}
