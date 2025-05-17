package io.quarkus.poc.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.poc.adapter.out.database.entity.InvoiceGroupEntity;
import io.quarkus.poc.adapter.out.database.entity.InvoiceStatementEntity;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.port.out.InvoiceGroupPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.UUID;

@ApplicationScoped
public class InvoiceGroupRepository implements PanacheRepositoryBase<InvoiceGroupEntity, UUID>, InvoiceGroupPort {
    @Override
    public void save(InvoiceGroupAggregateRoot aggregateRoot) {
        var statements = new HashSet<InvoiceStatementEntity>();
        var statement = new InvoiceStatementEntity();
        statement.setId(aggregateRoot.getInvoiceGroup().getInvoiceStatement().getStatementNumber());
        statement.setStatus(aggregateRoot.getInvoiceGroup().getInvoiceStatement().getStatus());
        statements.add(statement);
        var InvoiceGroupEntity = new InvoiceGroupEntity(aggregateRoot.getInvoiceGroup().getId(), statements);
        statement.setInvoiceGroup(InvoiceGroupEntity);
        persist(InvoiceGroupEntity);
    }
}
