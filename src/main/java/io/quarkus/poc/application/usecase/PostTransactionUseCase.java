package io.quarkus.poc.application.usecase;

import io.quarkus.poc.application.command.PostingTransactionCommand;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;

public interface PostTransactionUseCase {
    InvoiceGroupAggregateRoot process(PostingTransactionCommand command);
}
