package com.posting.service.application.usecase;

import com.posting.service.adapter.out.database.repository.InvoiceGroupRepository;
import com.posting.service.application.command.PostingTransactionCommand;
import com.posting.service.application.handler.event.EventProcessor;
import com.posting.service.application.loader.InvoiceGroupAggregateRootLoader;
import com.posting.service.application.uow.UnitOfWork;
import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.model.entity.Transaction;
import com.posting.service.domain.port.in.PostUseCase;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
public class PostTransactionService implements PostUseCase {

    private final UnitOfWork unitOfWork;
    private final InvoiceGroupRepository invoiceGroupRepository;
    private final InvoiceGroupAggregateRootLoader loader;
    private final EventProcessor eventProcessor;



    public PostTransactionService(UnitOfWork unitOfWork, InvoiceGroupRepository invoiceGroupRepository, InvoiceGroupAggregateRootLoader loader, EventProcessor eventProcessor) {
        this.unitOfWork = unitOfWork;
        this.invoiceGroupRepository = invoiceGroupRepository;
        this.loader = loader;
        this.eventProcessor = eventProcessor;
    }

    @Override
    public InvoiceGroupAggregateRoot process(PostingTransactionCommand command) {
        log.info("Processing transaction for command {}", command);
        return unitOfWork.execute(() -> runInTransaction(command));
    }

    /**
     * Retorna um Supplier para buscar a transação original, se aplicável.
     * Útil para reversão, mas pode ser expandido para outros casos.
     */
    private Supplier<Optional<Transaction>> getOriginalFinder(PostingTransactionCommand command) {
        Supplier<Optional<Transaction>> result = Optional::empty;
        if (command.operationType().equals("R"))
            result = () -> invoiceGroupRepository.findOriginal(command.invoiceGroupId());
        return result;
    }

    private InvoiceGroupAggregateRoot runInTransaction(PostingTransactionCommand command) {
        var aggregateRoot = loader.loadOrCreate(command);
        var findOriginal = getOriginalFinder(command);
        var original = findOriginal.get();
        if (original.isPresent()) log.info("Updating aggregate root with original transaction");
        final var aggregateRootUpdate = invoiceGroupRepository.save(aggregateRoot);
        eventProcessor.processAndPublish(aggregateRootUpdate);
        return aggregateRoot;
    }
}
