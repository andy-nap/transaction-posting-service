package com.posting.service.adapter.in.delegate;

import com.posting.service.adapter.out.database.repository.InvoiceGroupRepository;
import com.posting.service.application.handler.event.AccountableEventHandler;
import com.posting.service.application.handler.event.BrandCreatedEventHandler;
import com.posting.service.application.handler.event.EventProcessor;
import com.posting.service.application.handler.event.TransactionCreatedEventHandler;
import com.posting.service.application.loader.InvoiceGroupAggregateRootLoader;
import com.posting.service.application.uow.UnitOfWork;
import com.posting.service.application.usecase.*;
import com.posting.service.domain.model.enums.OperationType;
import com.posting.service.domain.port.in.PostUseCase;
import com.posting.service.domain.port.out.EventPublisherPort;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PostUseCaseConfig {

    public static Map<OperationType, PostUseCase> buildHandlers(
            UnitOfWork unitOfWork,
            EventPublisherPort eventPublisher,
            InvoiceGroupRepository invoiceGroupRepository
    ) {
        var loader = new InvoiceGroupAggregateRootLoader(invoiceGroupRepository);

        var transactionCreatedEventHandler = new TransactionCreatedEventHandler();
        var brandCreatedEventHandler = new BrandCreatedEventHandler();
        var accountableEventHandler = new AccountableEventHandler();
        var eventProcessor = new EventProcessor(
                List.of(transactionCreatedEventHandler, brandCreatedEventHandler, accountableEventHandler),
                eventPublisher
        );

        Map<OperationType, PostUseCase> handlers = new EnumMap<>(OperationType.class);
        handlers.put(OperationType.POSTING, new PostTransactionService(
                unitOfWork, invoiceGroupRepository, loader, eventProcessor));
        handlers.put(OperationType.BRAND, new PostBrandDataService(
                unitOfWork, eventPublisher, invoiceGroupRepository, loader, List.of(brandCreatedEventHandler)));
        handlers.put(OperationType.REVERSAL, new PostReversalTransactionService(
                unitOfWork, eventPublisher, invoiceGroupRepository, loader, Collections.emptyList()));
        handlers.put(OperationType.ANNULATION, new PostAnnulationTransactionService(
                unitOfWork, eventPublisher, invoiceGroupRepository, loader, Collections.emptyList()));
        handlers.put(OperationType.LOSS, new PostLossTransactionService(
                unitOfWork, eventPublisher, invoiceGroupRepository, loader, Collections.emptyList()));

        return handlers;
    }
}