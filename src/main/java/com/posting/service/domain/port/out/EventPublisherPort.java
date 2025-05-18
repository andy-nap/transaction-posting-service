package com.posting.service.domain.port.out;

import com.posting.service.domain.model.event.DomainEvent;

public interface EventPublisherPort {

    void publish(DomainEvent event);
}
