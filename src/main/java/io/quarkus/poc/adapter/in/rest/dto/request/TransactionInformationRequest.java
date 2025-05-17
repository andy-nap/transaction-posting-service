package io.quarkus.poc.adapter.in.rest.dto.request;

import java.math.BigDecimal;

public record TransactionInformationRequest(BigDecimal transactionValue, String transactionCode) {
}
