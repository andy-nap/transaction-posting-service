package io.quarkus.poc.adapter.out.message;

import io.quarkus.poc.domain.model.event.DomainEvent;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public final class EventWrapper {
    private final DomainEvent data;
    private final Map<String, Object> headers;

    private EventWrapper(DomainEvent data, Map<String, Object> headers) {
        this.data = data; // Cópia defensiva do array
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers)); // Imutável e cópia defensiva
    }

    public static Builder builder() {
        return new Builder();
    }

    // Builder estático externo
    public static class Builder {
        private DomainEvent data;
        private final Map<String, Object> headers = new HashMap<>();



        public Builder data(DomainEvent data) {
            this.data = data;
            return this;
        }

        public Builder header(String key, String value) {
            Objects.requireNonNull(key, "header key cannot be null");
            Objects.requireNonNull(value, "header value cannot be null");
            headers.put(key, value);
            return this;
        }

        public Builder headers(Map<String, String> map) {
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    header(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public EventWrapper build() {
            if (data == null) throw new IllegalStateException("data must be set");
            return new EventWrapper(data, headers);
        }
    }
}