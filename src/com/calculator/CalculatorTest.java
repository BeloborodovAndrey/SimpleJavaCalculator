package com.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    Calculator calculator = new Calculator();
    @Test
    void calculateResultTest() {
        String  errorMessage = "It's wrong operation, try again";
        Assertions.assertEquals(calculator.calculateResult("a+b"), errorMessage);
        Assertions.assertEquals(calculator.calculateResult("1+b"), errorMessage);
        Assertions.assertEquals(calculator.calculateResult("1+V"), errorMessage);
        Assertions.assertEquals(calculator.calculateResult("1+1"), "2");
        Assertions.assertEquals(calculator.calculateResult("1+10"), "11");
        Assertions.assertEquals(calculator.calculateResult("10 + 10"), "20");
        Assertions.assertEquals(calculator.calculateResult("10+ 5"), "15");
        Assertions.assertEquals(calculator.calculateResult("10+ 11"), errorMessage);
        Assertions.assertEquals(calculator.calculateResult("V+ X"), "15");
        Assertions.assertEquals(calculator.calculateResult("VI+ X"), "16");
        Assertions.assertEquals(calculator.calculateResult("V+ IX"), "14");
        Assertions.assertEquals(calculator.calculateResult("VI+ XI"),errorMessage);
        Assertions.assertEquals(calculator.calculateResult("X+ VIII"), "18");
        Assertions.assertEquals(calculator.calculateResult("VI+ VIII"), "14");
        Assertions.assertEquals(calculator.calculateResult("VIII+ VIII"), "16");
    }
}