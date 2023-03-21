package com.progettoweb.civediamodomanibe.core.exception;

public class RestrictedActionException extends RuntimeException {

    public RestrictedActionException(String message, Object... args) {
        super(RestrictedActionException.generateMessage(message, args));
    }

    private static String generateMessage(String message, Object... args) {
        return String.format(message, args);
    }

}
