package com.posting.service.adapter.out.tx;

import com.posting.service.application.uow.UnitOfWork;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.faulttolerance.Retry;

@ApplicationScoped
public class JpaUnitOfWork implements UnitOfWork {

    @Override
    @Retry(maxRetries = 2, delay = 1000L, retryOn = Exception.class)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public <T> T execute(Work<T> work) {
        return work.run();
    }
}
