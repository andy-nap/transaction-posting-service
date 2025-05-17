package io.quarkus.poc.application.exception;

public class TechnicalException extends ApplicationException{

    protected TechnicalException(String messageId, String message, Throwable cause) {
        super(messageId, message, cause);
    }
}