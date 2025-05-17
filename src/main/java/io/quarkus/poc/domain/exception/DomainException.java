package io.quarkus.poc.domain.exception;

public abstract class DomainException extends RuntimeException {

    String messageId;

    protected DomainException(String messageId, String message) {
        super(message);
        this.messageId = messageId;
    }
}
