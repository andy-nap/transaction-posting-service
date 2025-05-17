package io.quarkus.poc.domain.port.out;

import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;

public interface InvoiceGroupPort {
    void save(InvoiceGroupAggregateRoot aggregateRoot);
}
