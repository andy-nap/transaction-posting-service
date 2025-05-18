package com.posting.service.adapter.in.rest.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record PostingTransactionRequest(UUID invoiceGroupCode,
                                        String currencyCode,
                                        String operationType,
                                        BigDecimal transactionValue,
                                        BrandInformationRequest brandInformation) {}
