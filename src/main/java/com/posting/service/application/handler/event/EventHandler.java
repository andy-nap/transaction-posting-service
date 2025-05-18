package com.posting.service.application.handler.event;

import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.model.event.DomainEvent;

import java.util.Optional;

public interface EventHandler {
    Optional<DomainEvent> handle(InvoiceGroupAggregateRoot root);
}
