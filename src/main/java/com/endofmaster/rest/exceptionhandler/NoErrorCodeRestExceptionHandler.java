package com.endofmaster.rest.exceptionhandler;

import com.endofmaster.rest.exception.BadRequestException;
import com.endofmaster.rest.exception.NotFoundException;
import com.endofmaster.rest.exception.ServerException;
import com.endofmaster.rest.exception.ServiceUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

/**
 * Created by ZM.Wang
 */
public class NoErrorCodeRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnhandledException(Exception e) {
        logger.error("Handle Exception", e); // we shall log the error for developer, but hide details from user!
        return new ResponseEntity<>("Internal server error.", new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Object> handleServerException(ServerException e) {
        logger.error("Handle ServerException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailableException e) {
        logger.error("Handle ServerException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), new HttpHeaders(), SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
        logger.error("Handle BadRequestException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        logger.error("Handle NotFoundException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), new HttpHeaders(), NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle BindException", e);
        return handleBindings(headers, status, e.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle MethodArgumentNotValidException", e);
        return handleBindings(headers, status, e.getBindingResult());
    }

    private ResponseEntity<Object> handleBindings(HttpHeaders headers, HttpStatus status, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errorDescription = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorDescription.append("[").append(fieldError.getField()).append("]")
                    .append(fieldError.getDefaultMessage()).append(';');
        }
        errorDescription.deleteCharAt(errorDescription.length() - 1);
        return new ResponseEntity<>(errorDescription.toString(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle HttpMessageNotReadableException", e);
        return new ResponseEntity<>("Message not readable", headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle HttpRequestMethodNotSupportedException", e);
        Set<HttpMethod> supportedMethods = e.getSupportedHttpMethods();
        if (!supportedMethods.isEmpty()) {
            headers.setAllow(supportedMethods);
        }
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle HttpMediaTypeNotSupportedException", e);
        List<MediaType> mediaTypes = e.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle HttpMediaTypeNotAcceptableException", e);
        List<MediaType> mediaTypes = e.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle MissingPathVariableException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle MissingServletRequestParameterException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle ServletRequestBindingException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle ConversionNotSupportedException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle TypeMismatchException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle HttpMessageNotWritableException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle MissingServletRequestPartException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Handle NoHandlerFoundException", e);
        return new ResponseEntity<>(e.getLocalizedMessage(), headers, status);
    }
}
