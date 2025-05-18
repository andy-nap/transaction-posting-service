package com.posting.service.adapter.out.message.outbox.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.outbox.quarkus.ExportedEvent;
import com.posting.service.adapter.out.message.EventWrapper;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

public class OutboxEvent implements ExportedEvent<String, JsonNode> {

    private final JsonNode jsonNode;
    private final Instant timestamp;
    private final String type;
    private final String eventType;
    private final String key;
    private final Map<String, Object> headers;

    public OutboxEvent(Instant createdAt, EventWrapper event, ObjectMapper mapper) {
        this.timestamp = createdAt;
        this.jsonNode = convertToJson(event.getData(), mapper);
        this.type = Objects.requireNonNull(event.getData()).getClass().getSimpleName();
        this.eventType = "event_type";
        this.key = event.getData().id();
        this.headers = event.getHeaders();
    }

    @Override
    public String getAggregateId() {
        return key;
    }

    @Override
    public String getAggregateType() {
        return type;
    }

    @Override
    public JsonNode getPayload() {
        return jsonNode;
    }

    @Override
    public String getType() {
        return eventType;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public Map<String, Object> getAdditionalFieldValues() {
        // no additional fields
        return Map.of("metadata", "valor");
    }

    private JsonNode convertToJson(Object data, ObjectMapper mapper) {
        try {
            Objects.requireNonNull(mapper, "mapper cannot be null");
            return mapper.valueToTree(data);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao converter Order para JSON", e);
        }
    }

}
