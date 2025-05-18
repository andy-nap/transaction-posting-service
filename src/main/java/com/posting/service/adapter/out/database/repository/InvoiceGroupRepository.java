package com.posting.service.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import com.posting.service.adapter.out.database.entity.InvoiceGroupEntity;
import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.model.entity.Transaction;
import com.posting.service.domain.port.out.InvoiceGroupPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class InvoiceGroupRepository implements
        PanacheRepositoryBase<InvoiceGroupEntity, UUID>, InvoiceGroupPort {
    @Override
    public InvoiceGroupAggregateRoot save(InvoiceGroupAggregateRoot aggregateRoot) {
        aggregateRoot.setId(UUID.randomUUID());
        return aggregateRoot;
    }

    @Override
    public Optional<Transaction> findOriginal(UUID uuid) {
        return Optional.empty();
    }
}
