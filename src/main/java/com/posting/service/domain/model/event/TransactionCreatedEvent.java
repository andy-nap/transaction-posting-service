package com.posting.service.domain.model.event;

public record TransactionCreatedEvent(String id) implements DomainEvent {
}
