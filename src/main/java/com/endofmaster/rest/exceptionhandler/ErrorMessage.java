package com.endofmaster.rest.exceptionhandler;


/**
 * @author YQ.Huang
 */
public class ErrorMessage {

    private String error;
    private String errorDescription;

    public ErrorMessage() {
    }

    public ErrorMessage(ErrorCode error, String errorDescription) {
        this(error.toString(), errorDescription);
    }

    public ErrorMessage(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
