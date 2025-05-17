package io.quarkus.poc.domain.port.out;

import io.quarkus.poc.domain.model.entity.Transaction;

public interface TransactionPort {
    void save(Transaction transaction);
}
