package com.progettoweb.civediamodomanibe.core.exception;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@ThrowsException
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleNoHandledException(Exception e)
    {
        return buildResponseEntity(new CustomJsonError(INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        return buildResponseEntity(new CustomJsonError(HttpStatus.NOT_FOUND, ex));
    }

    @ExceptionHandler(SecurityException.class)
    protected ResponseEntity<Object> handleSecurityException(SecurityException ex) {
        CustomJsonError customJsonError = new CustomJsonError(BAD_REQUEST);
        customJsonError.setMessage(ex.getMessage());
        return buildResponseEntity(customJsonError);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        CustomJsonError customJsonError = new CustomJsonError(BAD_REQUEST);
        customJsonError.setMessage(ex.getMessage());
        return buildResponseEntity(customJsonError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(new CustomJsonError(HttpStatus.CONFLICT, "Database error", ex.getCause()));
        }
        return buildResponseEntity(new CustomJsonError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new CustomJsonError(BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
        return buildResponseEntity(new CustomJsonError(HttpStatus.BAD_REQUEST, error, ex));
    }

    /**
     * Handle HttpMessageNotWritableException.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = "Error writing JSON output";
        return buildResponseEntity(new CustomJsonError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        CustomJsonError customJsonError = new CustomJsonError(BAD_REQUEST);
        customJsonError.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        customJsonError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(customJsonError);
    }

    /**
     * Handle Exception, handle generic Exception.class
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        CustomJsonError customJsonError = new CustomJsonError(BAD_REQUEST);
        customJsonError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName()));
        customJsonError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(customJsonError);
    }

    private ResponseEntity<Object> buildResponseEntity(CustomJsonError customJsonError) {
        return new ResponseEntity<>(customJsonError, customJsonError.getStatus());
    }

}

