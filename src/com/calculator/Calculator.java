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
    /*array of possible input string values*/
    String[] allowableValues = new String[]
            {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "+", "-", "/", "*"};
    /*list for comfortable working with  allowableValues*/
    ArrayList<String> allowableValuesList = new ArrayList<String>();
    /*string for error cases*/
    String errorMessage = "";
    /*arguments of operations*/
    private List args;
    /*arithmetic operation*/
    private String operand;

    public Calculator() {
        /*initialize allowableValuesList*/
        Collections.addAll(allowableValuesList, allowableValues);
        /*complement allowableValuesList integer values*/
        for (int i = 0; i < 11; i++) {
            allowableValuesList.add(((String.valueOf(i))));
        }
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
        return null;
    }

    /*define result value by operationSelection*/
    public String calculateResult() {
        if (errorMessage != "") {
            return errorMessage;
        }
        String result = operationSelection(Integer.valueOf(args.getItem(0)),
                Integer.valueOf(args.getItem(1)), operand);
        if (result == null) {
            return "illegal operation";
        }
        return result;
    }

    /*fill first argument*/
    private boolean fillFirstArgument(int operandIndex, char[] arrayChar) {
        if (operandIndex == 2) {
            if (!checkForTwoSymbols(operandIndex, arrayChar)) {
                return false;
            }
        }
        args.add(String.valueOf(arrayChar[operandIndex - 2]));
        return true;
    }

    private boolean checkForTwoSymbols(int operandIndex, char[] arrayChar) {
        if (!Character.isDigit(arrayChar[operandIndex - 1])) {
            return false;
        }
        int argument = Integer.parseInt(String.valueOf(arrayChar[operandIndex - 2] +
                arrayChar[operandIndex - 1]));
        if (argument != 10) {
            return false;
        }
        args.add(String.valueOf(argument));
        return true;
    }

    /**
     * inner class for parsing operations strings
     */
    public class OperationsParser {
        /**
         * @param operationString user input string operation
         */
        ArrayList<Integer> digits = new ArrayList<>();

        public void fillErrorMessage() {
            errorMessage = "It's wrong operation, try again";
        }

        public void parsingString(String operationString) {
            if (operationString == "") {
                fillErrorMessage();
                return;
            }
            /*delete all spaces*/
            String tempString = operationString.replaceAll(" ", "");
            /*convert to array of char*/
            char[] arrayChar = tempString.toCharArray();
            /*check for digits*/
            if (Character.isDigit(arrayChar[0])) {
                digitOperationAnalyze(arrayChar);
                return;
            }
            /*check for rome digits*/
            if (Arrays.asList(new Character[]{'I', 'X', 'V'}).contains(arrayChar[0])) {
                romeDigitsOperationAnalyze(arrayChar);
                return;
            }
            fillErrorMessage();
        }

        /**
         * working with rome digit arithmetic operation
         *
         * @param arrayChar operation string
         */
        private void romeDigitsOperationAnalyze(char[] arrayChar) {

        }

        /*recieve index of arithmetic operand*/
        private int getOperandIndex(char[] arrayChar) {
            int operandIndex = 0;
            for (int j = 1; j < arrayChar.length; j++) {
                /*checking for operand index*/
                if (Arrays.asList(new Character[]{'-', '+', '/', '*'}).contains(arrayChar[j])) {
                    operandIndex = j;
                }
            }
            return operandIndex;
        }

        /**
         * working with digit arithmetic operation
         *
         * @param arrayChar operation string
         */
        private void digitOperationAnalyze(char[] arrayChar) {
            /*digit operation string length can't be more then 5*/
            if (arrayChar.length > 5 || arrayChar.length < 3) {
                fillErrorMessage();
                return;
            }
            int operandIndex = getOperandIndex(arrayChar);
            if (operandIndex < 1 || operandIndex > 2) {
                fillErrorMessage();
                return;
            }
            /*fill first argument*/
            if (!fillFirstArgument(operandIndex, arrayChar) &&
                    (fillSecondArgument(operandIndex + 2, arrayChar))) {
               fillErrorMessage();
            }
        }

        private boolean fillSecondArgument(int operandIndex, char[] arrayChar) {
            if (arrayChar.length == 5) {
                return checkForTwoSymbols(operandIndex + 2, arrayChar);
            }
            if (arrayChar.length == 4) {
                switch (operandIndex) {
                    case 3:
                        return getOneSymbol(operandIndex, arrayChar);
                    case 4:
                        return checkForTwoSymbols(operandIndex + 2, arrayChar);
                }
            }
            if (arrayChar.length == 3) {
                switch (operandIndex) {
                    case 4:
                        return false;
                    case 3:
                       return getOneSymbol(operandIndex, arrayChar);
                }
            }
            return false;
        }

        private boolean getOneSymbol(int operandIndex, char[] arrayChar) {
            if (Character.isDigit(operandIndex - 1)) {
                args.add(String.valueOf(arrayChar[operandIndex - 1]));
                return true;
            } else {
                return false;
            }
        }
    }
}



