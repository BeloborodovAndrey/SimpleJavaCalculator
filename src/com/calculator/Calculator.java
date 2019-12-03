package com.calculator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author belob
 * Class that implements the basic functionality of arithmetic calculator operations
 */
public class Calculator implements ArithmeticOperations {

    /*class for parsing operations*/
    public OperationsParser operationsParser;

    public Calculator() {
        /*initialize class for parsing operations*/
        operationsParser = new OperationsParser();
    }

    /**
     * method for selecting requirement operation
     *
     * @param operation determines the operation that will be selected from ArithmeticOperations
     *
     * @return operation result
     */
    public String operationSelection(int arg1, int arg2, String operation) {
        switch (operation) {
            case ("+"):
                return String.valueOf(composition(arg1, arg2));
            case ("-"):
                return String.valueOf(subtraction(arg1, arg2));
            case ("/"):
                return String.valueOf(division(arg1, arg2));
            case ("*"):
                return String.valueOf(multiplication(arg1, arg2));
        }
        return "";
    }

    /*define result value by operationSelection*/
    public String calculateResult() {
        if (operationsParser.getErrorMessage() != "") {
            return operationsParser.getErrorMessage() ;
        }
        String result = operationSelection(Integer.valueOf(operationsParser.getArgs().getItem(0)),
                Integer.valueOf(operationsParser.getArgs().getItem(1)), operationsParser.getOperand());
        if (result == null) {
            return "illegal operation";
        }
        return result;
    }
}



