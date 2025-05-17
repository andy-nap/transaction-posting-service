package io.quarkus.poc.adapter.in.rest.dto.request;

import io.quarkus.poc.domain.model.enums.OperationType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record PostingTransactionRequest(UUID invoiceGroupCode,
                                        String currencyCode,
                                        OperationType operationType,
                                        BigDecimal transactionValue,
                                        BrandInformationRequest brandInformation) {}
