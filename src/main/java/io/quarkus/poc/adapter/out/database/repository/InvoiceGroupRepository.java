package io.quarkus.poc.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.poc.adapter.out.database.entity.InvoiceGroupEntity;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.port.out.InvoiceGroupPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class InvoiceGroupRepository implements
        PanacheRepositoryBase<InvoiceGroupEntity, UUID>, InvoiceGroupPort {
    @Override
    public InvoiceGroupAggregateRoot save(InvoiceGroupAggregateRoot aggregateRoot) {
        aggregateRoot.setId(UUID.randomUUID());
        return aggregateRoot;
    }
}
