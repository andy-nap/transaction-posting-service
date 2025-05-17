package io.quarkus.poc.adapter.out.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.Set;
import java.util.UUID;

@Entity(name = "invoice_group_configuration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FilterDef(name = "openCutStatusFilter", parameters = { @ParamDef(name = "open", type = Short.class), @ParamDef(name = "cut", type = Short.class)})
public class InvoiceGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @OneToMany(mappedBy = "invoiceGroup", cascade = CascadeType.ALL)
    @Filter(name = "openCutStatusFilter", condition = "status in (:cut, :open)")
    private Set<InvoiceStatementEntity> invoiceStatements;
}
