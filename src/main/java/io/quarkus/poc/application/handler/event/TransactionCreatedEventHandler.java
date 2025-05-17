package io.quarkus.poc.application.handler.event;

import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.model.event.DomainEvent;
import io.quarkus.poc.domain.model.event.TransactionCreatedEvent;

import java.util.Optional;

public class TransactionCreatedEventHandler implements EventHandler {

    @Override
    public Optional<DomainEvent> handle(InvoiceGroupAggregateRoot root) {
        return Optional.of(new TransactionCreatedEvent("novo"));
    }
}
