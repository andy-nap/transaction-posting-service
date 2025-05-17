package io.quarkus.poc.domain.model.event;

public record BrandCreateEvent(String id) implements DomainEvent {
}
