package io.quarkus.poc.adapter.out.message.enricher;

import io.opentelemetry.api.baggage.Baggage;
import io.quarkus.poc.adapter.out.message.EventWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ServiceOriginEnricher implements EventWrapperEnricher {
    private static final String BAG_KEY = "servico_origem";

    @Inject
    @ConfigProperty(name = "app.service-name")
    String serviceName;

    @Override
    public void enrich(EventWrapper.Builder builder) {
        String svc = Baggage.current().getEntryValue(BAG_KEY);
        builder.header("ce_servico_origem", svc != null ? svc : serviceName);
    }
}