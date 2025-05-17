package io.quarkus.poc.domain.port.out;

import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;

public interface InvoiceGroupPort {
    InvoiceGroupAggregateRoot save(InvoiceGroupAggregateRoot aggregateRoot);
}
