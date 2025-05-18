package io.quarkus.poc.domain.port.out;

import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.model.entity.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceGroupPort {
    InvoiceGroupAggregateRoot save(InvoiceGroupAggregateRoot aggregateRoot);

    Optional<Transaction> findOriginal(UUID uuid);
}
