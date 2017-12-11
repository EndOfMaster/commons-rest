package com.jifenke.rest.exception;


/**
 * @author ZM.Wang
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        this("Not found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
