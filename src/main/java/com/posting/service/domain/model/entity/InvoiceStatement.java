package com.posting.service.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class InvoiceStatement {

    private UUID statementNumber;
    private Short status;
    private Transaction transaction;
    private Transaction originalTransaction;

    public boolean isOpen() {
        return status == 1;
    }
}
