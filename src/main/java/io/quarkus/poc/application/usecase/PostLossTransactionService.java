package io.quarkus.poc.application.usecase;

import io.quarkus.poc.adapter.out.database.repository.InvoiceGroupRepository;
import io.quarkus.poc.application.command.PostingTransactionCommand;
import io.quarkus.poc.application.handler.event.EventHandler;
import io.quarkus.poc.application.loader.InvoiceGroupAggregateRootLoader;
import io.quarkus.poc.application.uow.UnitOfWork;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.port.in.PostUseCase;
import io.quarkus.poc.domain.port.out.EventPublisherPort;
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
