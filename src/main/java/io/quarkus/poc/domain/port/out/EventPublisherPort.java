package io.quarkus.poc.domain.port.out;

import io.quarkus.poc.domain.model.event.DomainEvent;

public interface EventPublisherPort {

    void publish(DomainEvent event);
}
