package io.quarkus.poc.application.exception;

public class FunctionalException extends ApplicationException{

    protected FunctionalException(String messageId, String message, Throwable cause) {
        super(messageId, message, cause);
    }
}
