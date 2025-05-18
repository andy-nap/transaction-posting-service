package com.posting.service.application.handler.event;

import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.model.event.DomainEvent;
import com.posting.service.domain.model.event.TransactionCreatedEvent;

import java.util.Optional;

public class TransactionCreatedEventHandler implements EventHandler {

    @Override
    public Optional<DomainEvent> handle(InvoiceGroupAggregateRoot root) {
        return Optional.of(new TransactionCreatedEvent("novo"));
    }
}
