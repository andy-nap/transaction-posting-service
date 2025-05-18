package io.quarkus.poc.application.loader;

import io.quarkus.poc.adapter.out.database.repository.InvoiceGroupRepository;
import io.quarkus.poc.application.command.PostingTransactionCommand;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
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
