package com.endofmaster.rest.exception;

/**
 * @author ZM.Wang
 */
public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException() {
        super();
    }
}
