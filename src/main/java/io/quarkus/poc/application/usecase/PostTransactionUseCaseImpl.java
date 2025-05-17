package io.quarkus.poc.application.usecase;

import io.quarkus.poc.adapter.out.database.repository.BrandInformationRepository;
import io.quarkus.poc.adapter.out.database.repository.InvoiceGroupRepository;
import io.quarkus.poc.adapter.out.database.repository.InvoiceStatementRepository;
import io.quarkus.poc.adapter.out.database.repository.TransactionRepository;
import io.quarkus.poc.application.command.PostingTransactionCommand;
import io.quarkus.poc.application.uow.UnitOfWork;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.model.entity.BrandInformation;
import io.quarkus.poc.domain.model.entity.InvoiceGroup;
import io.quarkus.poc.domain.model.entity.InvoiceStatement;
import io.quarkus.poc.domain.model.entity.Transaction;
import io.quarkus.poc.domain.port.out.EventPublisherPort;
import jakarta.persistence.LockModeType;

import java.util.HashSet;

public class PostTransactionUseCaseImpl implements PostTransactionUseCase {

    private final UnitOfWork unitOfWork;
    private final EventPublisherPort eventPublisher;
    private final InvoiceGroupRepository invoiceGroupRepository;
    private final InvoiceStatementRepository invoiceStatementRepository;
    private final TransactionRepository transactionRepository;
    private final BrandInformationRepository brandInformationRepository;


    public PostTransactionUseCaseImpl(UnitOfWork unitOfWork, EventPublisherPort eventPublisher, InvoiceGroupRepository invoiceGroupRepository, InvoiceStatementRepository invoiceStatementRepository, TransactionRepository transactionRepository, BrandInformationRepository brandInformationRepository) {
        this.unitOfWork = unitOfWork;
        this.eventPublisher = eventPublisher;
        this.invoiceGroupRepository = invoiceGroupRepository;
        this.invoiceStatementRepository = invoiceStatementRepository;
        this.transactionRepository = transactionRepository;
        this.brandInformationRepository = brandInformationRepository;
    }

    @Override
    public InvoiceGroupAggregateRoot process(PostingTransactionCommand command) {
        var result = unitOfWork.execute(() -> {
            var aggregateRoot = InvoiceGroupAggregateRoot.builder().invoiceGroup(InvoiceGroup.builder()
                    .invoiceStatement(InvoiceStatement.builder()
                            .status((short) 1)
                            .build())
                    .build()).build();
            var invoiceGroup = invoiceGroupRepository.findByIdOptional(command.invoiceGroupId(), LockModeType.PESSIMISTIC_WRITE);
            if (invoiceGroup.isPresent()) {
                aggregateRoot.getInvoiceGroup().setId(invoiceGroup.get().getId());
                aggregateRoot.getInvoiceGroup().getInvoiceStatement()
                        .setStatementNumber(invoiceGroup.get().getInvoiceStatements().isEmpty() ? null : invoiceGroup.get().getInvoiceStatements().iterator().next().getId());
            }
            aggregateRoot.getInvoiceGroup().getInvoiceStatement().setTransaction(
                    Transaction.builder().transactionCurrency(command.currencyCode())
                            .transactionValue(command.transactionValue())
                            .brandInformation(command.brandInformationRequest() != null ? BrandInformation.builder()
                                    .authCode(command.brandInformationRequest().authCode()).build() : null).build()
            );
            invoiceGroupRepository.save(aggregateRoot);
            transactionRepository.save(aggregateRoot.getInvoiceGroup().getInvoiceStatement().getTransaction());
            //aggregateRoot.getEventSet().forEach(eventPublisher::publish);
            ;
            return aggregateRoot;
        });
        return null;
    }
}
