package com.posting.service.application.handler.event;

import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.port.out.EventPublisherPort;

import java.util.List;
import java.util.Optional;

public class EventProcessor {

    private final List<EventHandler> eventHandlers;
    private final EventPublisherPort eventPublisher;

    public EventProcessor(List<EventHandler> eventHandlers, EventPublisherPort eventPublisher) {
        this.eventHandlers = eventHandlers;
        this.eventPublisher = eventPublisher;
    }

    public void processAndPublish(InvoiceGroupAggregateRoot root) {
        var events = eventHandlers.stream().map(handler -> handler.handle(root))
                .flatMap(Optional::stream).toList();
        events.forEach(eventPublisher::publish);
    }
}
