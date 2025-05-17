package io.quarkus.poc.adapter.in.mapper;

import io.quarkus.poc.adapter.in.rest.dto.request.PostingTransactionRequest;
import io.quarkus.poc.application.command.PostingTransactionCommand;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CommandMapper {
    public PostingTransactionCommand toCommand(PostingTransactionRequest request) {
        if(request == null) return null;
        return new PostingTransactionCommand(request.invoiceGroupCode(), request.currencyCode(), request.transactionValue(), request.brandInformation());
    }
}
