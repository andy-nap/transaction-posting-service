package io.quarkus.poc.domain.model.aggregate;

import io.quarkus.poc.domain.exception.BusinessRuleException;
import io.quarkus.poc.domain.model.entity.InvoiceStatement;
import io.quarkus.poc.domain.model.entity.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
public class InvoiceGroupAggregateRoot {
    private UUID id;
    private InvoiceStatement invoiceStatement;

    public void addTransactionOnOpenInvoiceStatement(Transaction transaction) {
        Objects.requireNonNull(transaction, "Transaction must not be null");
        if(!invoiceStatement.isOpen()) throw new BusinessRuleException("Can not add transaction on closed invoice statement","Can not add transaction on closed invoice statement");
        this.invoiceStatement.setTransaction(transaction);
    }
}
