package io.quarkus.poc.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.poc.adapter.out.database.entity.TransactionEntity;
import io.quarkus.poc.domain.model.entity.Transaction;
import io.quarkus.poc.domain.port.out.TransactionPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<TransactionEntity, UUID>, TransactionPort {
    @Override
    public void save(Transaction transaction) {

    }
}
