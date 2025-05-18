package com.posting.service.adapter.in.rest.dto.request;

import java.math.BigDecimal;

public record TransactionInformationRequest(BigDecimal transactionValue, String transactionCode) {
}
