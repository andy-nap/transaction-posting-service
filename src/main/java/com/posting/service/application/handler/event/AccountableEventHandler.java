package com.posting.service.application.handler.event;

import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.model.event.AccountableEvent;
import com.posting.service.domain.model.event.DomainEvent;

import java.util.Optional;

public class AccountableEventHandler implements EventHandler {

    @Override
    public Optional<DomainEvent> handle(InvoiceGroupAggregateRoot root) {
        return Optional.of(new AccountableEvent("novo"));
    }
}
