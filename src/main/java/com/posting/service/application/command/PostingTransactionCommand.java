package com.posting.service.application.command;

import com.posting.service.adapter.in.rest.dto.request.BrandInformationRequest;

import java.math.BigDecimal;
import java.util.UUID;

public record PostingTransactionCommand(UUID invoiceGroupId, String currencyCode, String operationType, BigDecimal transactionValue,
                                        BrandInformationRequest brandInformationRequest) {
}
