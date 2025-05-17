package io.quarkus.poc.application.handler.event;

import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.model.event.AccountableEvent;
import io.quarkus.poc.domain.model.event.DomainEvent;

import java.util.Optional;

public class AccountableEventHandler implements EventHandler {

    @Override
    public Optional<DomainEvent> handle(InvoiceGroupAggregateRoot root) {
        return Optional.of(new AccountableEvent("novo"));
    }
}
