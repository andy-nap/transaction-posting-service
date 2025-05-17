package io.quarkus.poc.domain.model.event;

public record TransactionCreatedEvent(String id) implements DomainEvent {
}
