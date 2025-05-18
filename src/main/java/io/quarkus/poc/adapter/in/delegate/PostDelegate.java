package io.quarkus.poc.adapter.in.delegate;

import io.quarkus.poc.adapter.out.database.repository.BrandInformationRepository;
import io.quarkus.poc.adapter.out.database.repository.InvoiceGroupRepository;
import io.quarkus.poc.adapter.out.database.repository.InvoiceStatementRepository;
import io.quarkus.poc.adapter.out.database.repository.TransactionRepository;
import io.quarkus.poc.application.command.PostingTransactionCommand;
import io.quarkus.poc.application.handler.event.AccountableEventHandler;
import io.quarkus.poc.application.handler.event.BrandCreatedEventHandler;
import io.quarkus.poc.application.handler.event.EventProcessor;
import io.quarkus.poc.application.handler.event.TransactionCreatedEventHandler;
import io.quarkus.poc.application.loader.InvoiceGroupAggregateRootLoader;
import io.quarkus.poc.application.uow.UnitOfWork;
import io.quarkus.poc.application.usecase.*;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.model.enums.OperationType;
import io.quarkus.poc.domain.port.in.PostUseCase;
import io.quarkus.poc.domain.port.out.EventPublisherPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class PostDelegate implements PostUseCase {

    private final Map<OperationType , PostUseCase> postTransactionHandlers = new EnumMap<>(OperationType.class);

    @Inject
    public PostDelegate(UnitOfWork unitOfWork, EventPublisherPort eventPublisher, InvoiceGroupRepository invoiceGroupRepository, InvoiceStatementRepository invoiceStatementRepository, TransactionRepository transactionRepository, BrandInformationRepository brandInformationRepository) {
        var loader = new InvoiceGroupAggregateRootLoader(invoiceGroupRepository);
        var transactionCreatedEventHandler = new TransactionCreatedEventHandler();
        var brandCreatedEventHandler = new BrandCreatedEventHandler();
        var accountableEventHandler = new AccountableEventHandler();
        var eventProcessor = new EventProcessor(List.of(transactionCreatedEventHandler, brandCreatedEventHandler, accountableEventHandler), eventPublisher);
        this.postTransactionHandlers.put(OperationType.POSTING, new PostTransactionService(unitOfWork,
                invoiceGroupRepository, loader, eventProcessor));
        this.postTransactionHandlers.put(OperationType.BRAND, new PostBrandDataService(unitOfWork, eventPublisher,
                invoiceGroupRepository, loader, List.of(brandCreatedEventHandler)));
        this.postTransactionHandlers.put(OperationType.REVERSAL, new PostReversalTransactionService(unitOfWork,
                eventPublisher, invoiceGroupRepository, loader, Collections.emptyList()));
        this.postTransactionHandlers.put(OperationType.ANNULATION, new PostAnnulationTransactionService(unitOfWork,
                eventPublisher, invoiceGroupRepository, loader, Collections.emptyList()));
        this.postTransactionHandlers.put(OperationType.LOSS, new PostLossTransactionService(unitOfWork, eventPublisher,
                invoiceGroupRepository, loader, Collections.emptyList()));

    }

    @Override
    public InvoiceGroupAggregateRoot process(PostingTransactionCommand command) {
        var service = postTransactionHandlers.get(OperationType.fromCode(command.operationType()));
        return service.process(command);
    }
}
