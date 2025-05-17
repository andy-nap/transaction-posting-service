package io.quarkus.poc.domain.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class InvoiceGroup {
    private UUID id;
    private InvoiceStatement invoiceStatement;
}
