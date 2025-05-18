package com.posting.service.domain.port.out;

import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.model.entity.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceGroupPort {
    InvoiceGroupAggregateRoot save(InvoiceGroupAggregateRoot aggregateRoot);

    Optional<Transaction> findOriginal(UUID uuid);
}
