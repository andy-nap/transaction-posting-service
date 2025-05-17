package io.quarkus.poc.domain.model.event;

public record AccountableEvent(String id) implements DomainEvent {
}
