package com.posting.service.adapter.out.message.enricher;


import com.posting.service.adapter.out.message.EventWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class EnvironmentEnricher implements EventWrapperEnricher {

    @ConfigProperty(name = "app.env")
    String environment;

    @Override
    public void enrich(EventWrapper.Builder builder) {
        builder.header("ce_environment", environment != null ? environment : "dev");
    }
}