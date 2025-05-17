package io.quarkus.poc.application.usecase;

import io.quarkus.poc.adapter.out.database.repository.InvoiceGroupRepository;
import io.quarkus.poc.application.command.PostingTransactionCommand;
import io.quarkus.poc.application.handler.event.EventHandler;
import io.quarkus.poc.application.loader.finder.InvoiceGroupAggregateRootLoader;
import io.quarkus.poc.application.uow.UnitOfWork;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import io.quarkus.poc.domain.port.in.PostTransactionUseCase;
import io.quarkus.poc.domain.port.out.EventPublisherPort;

import java.util.List;
import java.util.Optional;

public class PostTransactionService implements PostTransactionUseCase {

    private final UnitOfWork unitOfWork;
    private final EventPublisherPort eventPublisher;
    private final InvoiceGroupRepository invoiceGroupRepository;
    private final InvoiceGroupAggregateRootLoader loader;
    private final List<EventHandler> eventHandlers;


    public PostTransactionService(UnitOfWork unitOfWork, EventPublisherPort eventPublisher, InvoiceGroupRepository invoiceGroupRepository, InvoiceGroupAggregateRootLoader loader, List<EventHandler> eventHandlers) {
        this.unitOfWork = unitOfWork;
        this.eventPublisher = eventPublisher;
        this.invoiceGroupRepository = invoiceGroupRepository;
        this.loader = loader;
        this.eventHandlers = eventHandlers;
    }

    @Override
    public InvoiceGroupAggregateRoot process(PostingTransactionCommand command) {
        return unitOfWork.execute(() -> runInTransaction(command));
    }

    private InvoiceGroupAggregateRoot runInTransaction(PostingTransactionCommand command) {
            var aggregateRoot = loader.loadOrCreate(command);
            final var aggregateRootUpdate = invoiceGroupRepository.save(aggregateRoot);
            var events = eventHandlers.stream().map(handler -> handler.handle(aggregateRootUpdate))
                    .flatMap(Optional::stream).toList();
            events.forEach(eventPublisher::publish);
            return aggregateRoot;
    }
}
