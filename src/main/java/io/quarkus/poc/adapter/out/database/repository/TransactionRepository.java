package io.quarkus.poc.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.poc.adapter.out.database.entity.BrandInformationEntity;
import io.quarkus.poc.adapter.out.database.entity.InvoiceGroupEntity;
import io.quarkus.poc.adapter.out.database.entity.InvoiceStatementEntity;
import io.quarkus.poc.adapter.out.database.entity.TransactionEntity;
import io.quarkus.poc.domain.model.entity.Transaction;
import io.quarkus.poc.domain.port.out.TransactionPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.UUID;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<TransactionEntity, UUID>, TransactionPort {
    @Override
    public void save(Transaction transaction) {
        var brandInformationEntity = transaction.getBrandInformation() != null ? new BrandInformationEntity() : null;
        if(brandInformationEntity != null) {
            brandInformationEntity.setAuthCode(transaction.getBrandInformation().getAuthCode());
        }
        var transactionEntity = new TransactionEntity(null, transaction.getTransactionValue(),
                transaction.getTransactionCurrency(), brandInformationEntity, null);

        persist(transactionEntity);
    }
}
