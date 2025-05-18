package com.posting.service.domain.port.in;

import com.posting.service.application.command.PostingTransactionCommand;
import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;

public interface PostUseCase {
    InvoiceGroupAggregateRoot process(PostingTransactionCommand command);
}
