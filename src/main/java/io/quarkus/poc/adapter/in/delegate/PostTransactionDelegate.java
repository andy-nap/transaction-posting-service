package io.quarkus.poc.adapter.in.delegate;

import io.quarkus.poc.adapter.out.database.repository.BrandInformationRepository;
import io.quarkus.poc.adapter.out.database.repository.InvoiceGroupRepository;
import io.quarkus.poc.adapter.out.database.repository.InvoiceStatementRepository;
import io.quarkus.poc.adapter.out.database.repository.TransactionRepository;
import io.quarkus.poc.application.command.PostingTransactionCommand;
import io.quarkus.poc.application.uow.UnitOfWork;
import io.quarkus.poc.application.usecase.PostTransactionUseCase;
import io.quarkus.poc.application.usecase.PostTransactionUseCaseImpl;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.port.out.EventPublisherPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PostTransactionDelegate implements PostTransactionUseCase {

    private final PostTransactionUseCaseImpl postTransactionUseCase;

    @Inject
    public PostTransactionDelegate(UnitOfWork unitOfWork, EventPublisherPort eventPublisher, InvoiceGroupRepository invoiceGroupRepository, InvoiceStatementRepository invoiceStatementRepository, TransactionRepository transactionRepository, BrandInformationRepository brandInformationRepository) {
        this.postTransactionUseCase = new PostTransactionUseCaseImpl(unitOfWork, eventPublisher, invoiceGroupRepository, invoiceStatementRepository, transactionRepository, brandInformationRepository);
    }
    @Override
    public InvoiceGroupAggregateRoot process(PostingTransactionCommand command) {
        return postTransactionUseCase.process(command);
    }
}
