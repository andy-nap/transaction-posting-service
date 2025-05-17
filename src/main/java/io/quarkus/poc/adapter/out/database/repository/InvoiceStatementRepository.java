package io.quarkus.poc.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.poc.adapter.out.database.entity.InvoiceStatementEntity;
import io.quarkus.poc.domain.port.out.InvoiceStatementPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class InvoiceStatementRepository implements
        PanacheRepositoryBase<InvoiceStatementEntity, UUID>, InvoiceStatementPort {
}
