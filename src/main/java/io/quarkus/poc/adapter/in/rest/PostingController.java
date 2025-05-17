package io.quarkus.poc.adapter.in.rest;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.quarkus.poc.adapter.in.mapper.CommandMapper;
import io.quarkus.poc.adapter.in.rest.dto.request.PostingTransactionRequest;
import io.quarkus.poc.application.usecase.PostTransactionUseCase;
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
    final PostTransactionUseCase postTransactionUseCase;

    @Inject
    public PostingController(MeterRegistry registry, CommandMapper mapper, PostTransactionUseCase postTransactionUseCase) {
        this.requestCounter = registry.counter("requests_total", "type", "http");
        this.mapper = mapper;
        this.postTransactionUseCase = postTransactionUseCase;
    }

    @POST
    @WithSpan
    public InvoiceGroupAggregateRoot post(PostingTransactionRequest request) {
        System.out.printf("Requisição recebida:");
        var cmd = mapper.toCommand(request);
        return postTransactionUseCase.process(cmd);
    }

    @GET
    @Path("/increment")
    public String incrementCounter() {
        requestCounter.increment();
        return "Métrica incrementada!";
    }
}
