package io.quarkus.poc.adapter.out.message;

import io.quarkus.poc.domain.model.event.DomainEvent;
import io.quarkus.poc.domain.port.out.EventPublisherPort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventPublisher implements EventPublisherPort {

    public void publish(DomainEvent event) {
    }
}
