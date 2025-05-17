package io.quarkus.poc.adapter.out.tx;

import io.quarkus.poc.adapter.out.message.EventPublisher;
import io.quarkus.poc.application.uow.UnitOfWork;
import io.quarkus.poc.domain.port.out.EventPublisherPort;
import io.smallrye.reactive.messaging.kafka.KafkaProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class JpaUnitOfWork implements UnitOfWork {

    @Override
    @Retry(
            maxRetries = 5,
            delay = 500L
    )
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public <T> T execute(Work<T> work) {
        return work.run();
    }
}
