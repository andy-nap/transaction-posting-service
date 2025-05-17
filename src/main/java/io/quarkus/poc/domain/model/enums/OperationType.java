package io.quarkus.poc.domain.model.enums;

import io.quarkus.poc.domain.exception.BusinessRuleException;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum OperationType {
    POSTING("P"),
    BRAND("B"),
    REVERSAL("R"),
    ANNULATION("A"),
    LOSS("L");

    private final String code;

    OperationType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    private static final Map<String, OperationType> BY_CODE =
            Arrays.stream(values())
                    .collect(Collectors.toUnmodifiableMap(
                            t -> t.code.toUpperCase(), Function.identity()));

    public static OperationType fromCode(String code) {
        var result = BY_CODE.get(code == null ? "" : code.toUpperCase());
        if (result == null) {
            throw new BusinessRuleException("invalid-argument", "Invalid operation type code: " + code);
        }
        return result;
    }
}
