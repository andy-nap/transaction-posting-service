package io.quarkus.poc;

import io.cucumber.java.pt.*;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
    int a, b, resultado;

    @Dado("eu tenho o número {int}")
    public void eu_tenho_o_numero(int numero) {
        if (a == 0) a = numero;
        else b = numero;
    }

    @Quando("eu somo os números")
    public void eu_somo_os_numeros() {
        resultado = a + b;
    }

    @Então("o resultado deve ser {int}")
    public void o_resultado_deve_ser(int esperado) {
        assertEquals(esperado, resultado);
    }
}