package com.posting.service.adapter.out.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.outbox.quarkus.ExportedEvent;
import com.posting.service.adapter.out.message.enricher.EventWrapperEnricher;
import com.posting.service.adapter.out.message.outbox.event.OutboxEvent;
import com.posting.service.domain.model.event.DomainEvent;
import com.posting.service.domain.port.out.EventPublisherPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@ApplicationScoped
@Slf4j
public class EventPublisher implements EventPublisherPort {

    @Inject
    Event<ExportedEvent<?, ?>> publisher;
    @Inject
    ObjectMapper mapper;

    @Inject
    Instance<EventWrapperEnricher> enrichers;

    public void publish(DomainEvent event) {
        var builder = EventWrapper.builder()
                .data(event);
        enrichers.forEach(e -> e.enrich(builder));
        var wrapper = builder.build();

        publisher.fire(new OutboxEvent(Instant.now(), wrapper, mapper));
        log.info("Publishing event: {}", event);
    }

}
