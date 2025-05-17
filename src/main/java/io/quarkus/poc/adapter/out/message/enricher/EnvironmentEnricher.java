package io.quarkus.poc.adapter.out.message.enricher;


import io.quarkus.poc.adapter.out.message.EventWrapper;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class EnvironmentEnricher implements EventWrapperEnricher {

    @ConfigProperty(name = "app.env")
    String environment;

    @Override
    public EventWrapper enrich(EventWrapper envelope) {
        return envelope.withHeader("ce_environment", environment != null ? environment : "dev");
    }
}