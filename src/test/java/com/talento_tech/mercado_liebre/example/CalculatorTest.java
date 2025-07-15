package com.talento_tech.mercado_liebre.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equals;

import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {

    @Test
    @DisplayName("Debería sumar dos números correctamente")
    void shouldAddTwoNumbers(){
        Calculator calculator = new Calculator();

        int expected = 7;
        int actual = calculator.add(4,3);
        Assertions.assertEquals(expected, actual, "La suma debe ser 7");
    }

}
