package com.jifenke.rest.exception;

/**
 * @author ZM.Wang
 */
public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException() {
        this("Service unavailable");
    }

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
