package io.quarkus.poc.domain.model.aggregate;

import io.quarkus.poc.domain.model.entity.InvoiceGroup;
import io.quarkus.poc.domain.model.event.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class InvoiceGroupAggregateRoot {
    private InvoiceGroup invoiceGroup;
    private Set<DomainEvent> eventSet;
}
