package com.calculator;

/**
 * @author belob
 * Class that implements the basic functionality of arithmetic calculator operations
 */
public class Calculator implements ArithmeticOperations {

    /*class for parsing operations*/
    public OperationsParser operationsParser;
    /*variable for exceptions messages*/
    public String errMessage;

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
        try {
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
        } catch (ArithmeticException ex) {
            return "illegal operation";
        } catch (Exception ex) {
            errMessage = ex.getMessage();
            return "illegal operation";
        }
    }

    /*define result value by operationSelection*/
    public String calculateResult(String operation) {
        try {
            errMessage = "";
            if (operationsParser == null) {
                throw new NullPointerException("You must initialize operationsParser before");
            }
            /*translate operation to arithmetic*/
            operationsParser.parsingString(operation);
            if (operationsParser.getErrorMessage() != "") {
                return operationsParser.getErrorMessage();
            }
            String result = operationSelection(Integer.valueOf(operationsParser.getArgs().getItem(0)),
                    Integer.valueOf(operationsParser.getArgs().getItem(1)), operationsParser.getOperand());
            if (result == null) {
                return "illegal operation";
            }
            if (!operationsParser.getArabParser()) {
                result = new RomeConverter().convertRomeDigitsResult(Integer.parseInt(result));
            }
            return result;
        } catch (Exception ex) {
            errMessage = ex.getMessage();
            return "";
        }
    }

}



