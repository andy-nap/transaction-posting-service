package io.quarkus.poc.application.exception;

public abstract class ApplicationException extends RuntimeException {

    String messageId;

    protected ApplicationException(String messageId, String message, Throwable cause) {
        super(message, cause);
        this.messageId = messageId;
    }
}
