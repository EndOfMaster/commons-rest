package com.jifenke.rest.exception;

/**
 * @author ZM.Wang
 */
public class ServerException extends RuntimeException {

    public ServerException() {
        this("Internal server error");
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
