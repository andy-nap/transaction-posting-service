package com.posting.service.application.usecase;

import com.posting.service.adapter.out.database.repository.InvoiceGroupRepository;
import com.posting.service.application.command.PostingTransactionCommand;
import com.posting.service.application.handler.event.EventHandler;
import com.posting.service.application.loader.InvoiceGroupAggregateRootLoader;
import com.posting.service.application.uow.UnitOfWork;
import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.port.in.PostUseCase;
import com.posting.service.domain.port.out.EventPublisherPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PostLossTransactionService implements PostUseCase {

    private final UnitOfWork unitOfWork;
    private final EventPublisherPort eventPublisher;
    private final InvoiceGroupRepository invoiceGroupRepository;
    private final InvoiceGroupAggregateRootLoader loader;
    private final List<EventHandler> EventHandlers;

    public PostLossTransactionService(UnitOfWork unitOfWork, EventPublisherPort eventPublisher, InvoiceGroupRepository invoiceGroupRepository, InvoiceGroupAggregateRootLoader loader, List<EventHandler> EventHandlers) {
        this.unitOfWork = unitOfWork;
        this.eventPublisher = eventPublisher;
        this.invoiceGroupRepository = invoiceGroupRepository;
        this.loader = loader;
        this.EventHandlers = EventHandlers;
    }

    @Override
    public InvoiceGroupAggregateRoot process(PostingTransactionCommand command) {
        return unitOfWork.execute(() -> runInTransaction(command));
    }

    private InvoiceGroupAggregateRoot runInTransaction(PostingTransactionCommand command) {
        log.info("Processing loss transaction for command {}", command);
        return InvoiceGroupAggregateRoot.builder().build();
    }
}
