package io.quarkus.poc.adapter.out.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "open_invoice_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(length = 3, nullable = false)
    private String currencyCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_information_id", referencedColumnName = "id")
    private BrandInformationEntity brandInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_statement_id", referencedColumnName = "id")
    private InvoiceStatementEntity invoiceStatement;
}
