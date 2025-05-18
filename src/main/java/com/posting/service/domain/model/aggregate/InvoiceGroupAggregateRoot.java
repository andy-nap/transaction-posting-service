package com.posting.service.domain.model.aggregate;

import com.posting.service.domain.exception.BusinessRuleException;
import com.posting.service.domain.model.entity.InvoiceStatement;
import com.posting.service.domain.model.entity.Transaction;
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
