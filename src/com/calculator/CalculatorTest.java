package com.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    Calculator calculator = new Calculator();
    @Test
    void operationSelectionTest() {
        Calculator calculator = new Calculator();
        calculator.operationSelection(2,1,"/");
        Assertions.assertEquals(calculator.calculateResult(), "2");
        calculator.operationSelection(2,2,"*");
        Assertions.assertEquals(calculator.calculateResult(), "4");
        calculator.operationSelection(2,2,"+");
        Assertions.assertEquals(calculator.calculateResult(), "4");
        calculator.operationSelection(2,1,"-");
        Assertions.assertEquals(calculator.calculateResult(), "1");
        calculator.operationSelection(2,0,"/");
        Assertions.assertEquals(calculator.calculateResult(), "illegal operation");
    }

    @Test
    void calculateResultTest() {
        OperationsParser parser = new OperationsParser();
        parser.parsingString("a+b");
        Assertions.assertEquals(calculator.calculateResult(), parser.getErrorMessage());
        parser.parsingString("1+b");
        Assertions.assertEquals(calculator.calculateResult(), parser.getErrorMessage());
        parser.parsingString("1+V");
        Assertions.assertEquals(calculator.calculateResult(), parser.getErrorMessage());
        parser.parsingString("1+1");
        Assertions.assertEquals(calculator.calculateResult(), "2");
        parser.parsingString("1+10");
        Assertions.assertEquals(calculator.calculateResult(), "11");
        parser.parsingString("10 + 10");
        Assertions.assertEquals(calculator.calculateResult(), "20");
        parser.parsingString("10+ 10");
        Assertions.assertEquals(calculator.calculateResult(), "20");
        parser.parsingString("10+ 11");
        Assertions.assertEquals(calculator.calculateResult(), parser.getErrorMessage());
    }
}