package io.quarkus.poc.adapter.out.message;

import java.util.HashMap;
import java.util.Map;

public record EventWrapper(
        byte[] data,
        Map<String,String> headers) {

    public EventWrapper withHeader(String k, String v) {
        Map<String,String> m = new HashMap<>(headers);
        m.put(k, v);
        return new EventWrapper(data, Map.copyOf(m));
    }
}

