package com.posting.service.adapter.out.message.enricher;

import com.posting.service.adapter.out.message.EventWrapper;

public interface EventWrapperEnricher {
    void enrich(EventWrapper.Builder builder);
}
