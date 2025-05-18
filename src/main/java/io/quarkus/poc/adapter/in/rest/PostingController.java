package io.quarkus.poc.adapter.in.rest;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.quarkus.poc.adapter.in.mapper.CommandMapper;
import io.quarkus.poc.adapter.in.rest.dto.request.PostingTransactionRequest;
import io.quarkus.poc.domain.port.in.PostUseCase;
import io.quarkus.poc.domain.model.aggregate.InvoiceGroupAggregateRoot;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/postagens")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostingController {

    private final Counter requestCounter;
    final CommandMapper mapper;
    final PostUseCase postUseCase;

    @Inject
    public PostingController(MeterRegistry registry, CommandMapper mapper, PostUseCase postUseCase) {
        this.requestCounter = registry.counter("requests_total", "type", "http");
        this.mapper = mapper;
        this.postUseCase = postUseCase;
    }

    @POST
    @WithSpan
    public InvoiceGroupAggregateRoot post(PostingTransactionRequest request) {
        Baggage baggage = Baggage.builder()
                .put("servico_origem", "checkout-service")
                .put("correlation-id", "abc-xyz-123")
                .build();
        var contextComBaggage = baggage.storeInContext(Context.current());
        // Torna o contexto atual com baggage visível globalmente no thread atual
        try (Scope scope = contextComBaggage.makeCurrent()) {
            System.out.printf("Requisição recebida:");
            var cmd = mapper.toCommand(request);
            return postUseCase.process(cmd);
        }
    }

    @GET
    @Path("/increment")
    public String incrementCounter() {
        requestCounter.increment();
        return "Métrica incrementada!";
    }
}
