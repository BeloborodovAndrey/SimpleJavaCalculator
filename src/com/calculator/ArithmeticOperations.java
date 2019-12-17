package com.calculator;

/**
 * Functionality of arithmetic calculator operations
 */
public interface ArithmeticOperations {

    default int composition(int a, int b){

        return a + b;
    };

    default int subtraction(int a, int b){

        return a - b;
    }

    default int division(int a, int b){
        return  a / b;
    }

    default int multiplication(int a, int b){
        return a * b;
    }


}
