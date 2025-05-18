package com.posting.service.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import com.posting.service.adapter.out.database.entity.InvoiceStatementEntity;
import com.posting.service.domain.port.out.InvoiceStatementPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class InvoiceStatementRepository implements
        PanacheRepositoryBase<InvoiceStatementEntity, UUID>, InvoiceStatementPort {
}
