package com.posting.service.adapter.in.mapper;

import com.posting.service.adapter.in.rest.dto.request.PostingTransactionRequest;
import com.posting.service.application.command.PostingTransactionCommand;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CommandMapper {
    public PostingTransactionCommand toCommand(PostingTransactionRequest request) {
        if(request == null) return null;
        return new PostingTransactionCommand(request.invoiceGroupCode(), request.currencyCode(), request.operationType(), request.transactionValue(), request.brandInformation());
    }
}
