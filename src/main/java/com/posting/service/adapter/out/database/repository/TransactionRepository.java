package com.posting.service.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import com.posting.service.adapter.out.database.entity.TransactionEntity;
import com.posting.service.domain.model.entity.Transaction;
import com.posting.service.domain.port.out.TransactionPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<TransactionEntity, UUID>, TransactionPort {
    @Override
    public void save(Transaction transaction) {

    }
}
