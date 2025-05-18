package com.posting.service.adapter.in.delegate;

import com.posting.service.adapter.out.database.repository.InvoiceGroupRepository;
import com.posting.service.application.command.PostingTransactionCommand;
import com.posting.service.application.uow.UnitOfWork;
import com.posting.service.domain.model.aggregate.InvoiceGroupAggregateRoot;
import com.posting.service.domain.model.enums.OperationType;
import com.posting.service.domain.port.in.PostUseCase;
import com.posting.service.domain.port.out.EventPublisherPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class PostDelegate implements PostUseCase {

    private final Map<OperationType , PostUseCase> postTransactionHandlers;

    @Inject
    public PostDelegate(UnitOfWork unitOfWork,
                        EventPublisherPort eventPublisher,
                        InvoiceGroupRepository invoiceGroupRepository) {
        this.postTransactionHandlers = PostUseCaseConfig.buildHandlers(
                unitOfWork,
                eventPublisher,
                invoiceGroupRepository
        );
    }

    @Override
    public InvoiceGroupAggregateRoot process(PostingTransactionCommand command) {
        var service = postTransactionHandlers.get(OperationType.fromCode(command.operationType()));
        return service.process(command);
    }
}
