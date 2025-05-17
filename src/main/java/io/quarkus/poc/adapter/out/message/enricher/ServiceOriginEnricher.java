package io.quarkus.poc.adapter.out.message.enricher;

import io.opentelemetry.api.baggage.Baggage;
import io.quarkus.poc.adapter.out.message.EventWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ServiceOriginEnricher implements EventWrapperEnricher {
    private static final String BAG_KEY = "servico_origem";

    @ConfigProperty(name = "app.service-name")
    String serviceName;

    @Override
    public EventWrapper enrich(EventWrapper envelope) {
        String svc = Baggage.current().getEntryValue(BAG_KEY);
        return envelope.withHeader("ce_servico_origem", svc != null ? svc : serviceName);
    }
}