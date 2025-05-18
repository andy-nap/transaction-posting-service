package com.posting.service.application.loader;

import com.posting.service.adapter.out.database.repository.InvoiceGroupRepository;
import com.posting.service.application.command.PostingTransactionCommand;
import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvoiceGroupAggregateRootLoader {

    private final InvoiceGroupRepository invoiceGroupRepository;

    public InvoiceGroupAggregateRootLoader(InvoiceGroupRepository invoiceGroupRepository) {
        this.invoiceGroupRepository = invoiceGroupRepository;
    }

    public InvoiceGroupAggregateRoot loadOrCreate(PostingTransactionCommand command) {
        log.info("Loading or creating InvoiceGroupAggregateRoot for command {}", command);
        return InvoiceGroupAggregateRoot.builder().id(command.invoiceGroupId()).build();
    }
}
