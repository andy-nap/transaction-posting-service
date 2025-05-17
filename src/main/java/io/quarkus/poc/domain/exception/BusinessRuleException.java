package io.quarkus.poc.domain.exception;

public class BusinessRuleException extends DomainException{

    public BusinessRuleException(String messageId, String message) {
        super(messageId, message);
    }
}
