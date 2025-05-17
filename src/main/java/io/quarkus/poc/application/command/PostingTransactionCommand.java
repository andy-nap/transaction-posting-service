package io.quarkus.poc.application.command;

import io.quarkus.poc.adapter.in.rest.dto.request.BrandInformationRequest;
import io.quarkus.poc.adapter.in.rest.dto.request.TransactionInformationRequest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record PostingTransactionCommand(UUID invoiceGroupId, String currencyCode, BigDecimal transactionValue,
                                        BrandInformationRequest brandInformationRequest) {
}
