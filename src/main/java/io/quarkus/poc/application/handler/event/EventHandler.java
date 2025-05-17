package io.quarkus.poc.application.handler.event;

import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.model.event.DomainEvent;

import java.util.Optional;

public interface EventHandler {
    Optional<DomainEvent> handle(InvoiceGroupAggregateRoot root);
}
