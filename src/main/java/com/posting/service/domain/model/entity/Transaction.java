package com.posting.service.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Transaction {

    private BigDecimal transactionValue;
    private String transactionCurrency;
    private BrandInformation brandInformation;
}
