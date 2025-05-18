package com.posting.service.domain.exception;

public class BusinessRuleException extends DomainException{

    public BusinessRuleException(String messageId, String message) {
        super(messageId, message);
    }
}
