package com.posting.service.adapter.in.delegate;

import com.posting.service.adapter.out.database.repository.BrandInformationRepository;
import com.posting.service.adapter.out.database.repository.InvoiceGroupRepository;
import com.posting.service.adapter.out.database.repository.InvoiceStatementRepository;
import com.posting.service.adapter.out.database.repository.TransactionRepository;
import com.posting.service.application.command.PostingTransactionCommand;
import com.posting.service.application.handler.event.AccountableEventHandler;
import com.posting.service.application.handler.event.BrandCreatedEventHandler;
import com.posting.service.application.handler.event.EventProcessor;
import com.posting.service.application.handler.event.TransactionCreatedEventHandler;
import com.posting.service.application.loader.InvoiceGroupAggregateRootLoader;
import com.posting.service.application.uow.UnitOfWork;
import com.posting.service.application.usecase.*;
import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.model.enums.OperationType;
import com.posting.service.domain.port.in.PostUseCase;
import com.posting.service.domain.port.out.EventPublisherPort;
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
