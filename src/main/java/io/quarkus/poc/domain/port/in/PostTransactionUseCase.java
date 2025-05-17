package io.quarkus.poc.domain.port.in;

import io.quarkus.poc.application.command.PostingTransactionCommand;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;

public interface PostTransactionUseCase {
    InvoiceGroupAggregateRoot process(PostingTransactionCommand command);
}
