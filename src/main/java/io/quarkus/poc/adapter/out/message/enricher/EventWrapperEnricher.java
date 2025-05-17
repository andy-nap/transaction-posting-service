package io.quarkus.poc.adapter.out.message.enricher;

import io.quarkus.poc.adapter.out.message.EventWrapper;

public interface EventWrapperEnricher {
    EventWrapper enrich(EventWrapper envelope);
}
