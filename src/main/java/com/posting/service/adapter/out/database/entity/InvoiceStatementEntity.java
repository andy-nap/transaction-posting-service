package com.posting.service.adapter.out.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "invoice_statement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InvoiceStatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private Short status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_group_configuration_id", referencedColumnName = "id")
    private InvoiceGroupEntity invoiceGroup;

}
