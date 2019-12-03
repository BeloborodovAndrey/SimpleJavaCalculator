package com.calculator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author belob
 * class
 */
public class OperationsParser {

    /*sides of arguments*/
    static final int LEFT_SIDE = 0;
    static final int RIGHT_SIDE = 1;
    /*storage of digits arguments*/
    ArrayList<Integer> digits = new ArrayList<>();
    /*array of possible input string values*/
    String[] allowableValues = new String[]
            {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    /*list for comfortable working with  allowableValues*/
    ArrayList<String> allowableValuesList = new ArrayList<String>();
    /*wrong operations flag*/
    private String errorMessage;
    /*arguments of operations*/
    private List args;
    /*arithmetic operand*/
    private String operand;

    public OperationsParser() {
        /*initialize allowableValuesList*/
        Collections.addAll(allowableValuesList, allowableValues);
    }

    public List getArgs() {
        return args;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getOperand() {
        return operand;
    }

    public void fillErrorMessage() {
        errorMessage = "It's wrong operation, try again";
    }

    /**
     * @param operationString user input string operation
     */
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
        /* get the index of arithmetic operand */
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

    /**
     * working with rome digit arithmetic operation
     *
     * @param arrayChar operation string
     */
    private void romeDigitsOperationAnalyze(char[] arrayChar) {
        /*rome digit operation string length can't be more then 9*/
        if (arrayChar.length > 9 || arrayChar.length < 3) {
            fillErrorMessage();
            return;
        }
        /* get the index of arithmetic operand */
        int operandIndex = getOperandIndex(arrayChar);
        if (operandIndex < 1 || operandIndex > 4) {
            fillErrorMessage();
            return;
        }
        int leftSideCount = getCountSymbols(LEFT_SIDE, operandIndex, arrayChar.length);
        int rightSideCount = getCountSymbols(RIGHT_SIDE, operandIndex, arrayChar.length) ;
        if(leftSideCount == -1 || rightSideCount == -1){
            fillErrorMessage();
            return;
        }
        //get left arg
        fillOperationArgs(leftSideCount,0, arrayChar);
        //get right arg
        fillOperationArgs(rightSideCount,operandIndex + 1, arrayChar);
    }

    private void fillOperationArgs(int countSymbols, int index, char[] arrayChar) {
        switch (countSymbols) {
            case (1):
                getParsedToDigitOneSymbol(arrayChar[index]);
                break;
            case (2):
                getParsedToDigitTwoSymbols(String.valueOf(arrayChar[index] + arrayChar[index + 1]));
                break;
            case (3):
                getParsedToDigitThreeSymbols(String.valueOf(arrayChar[index] + arrayChar[index + 1]
                        + arrayChar[index + 2]));
                break;
            case (4):
                getParsedToDigitFourSymbols(String.valueOf(arrayChar[index] + arrayChar[index + 1]
                        + arrayChar[index + 2] + arrayChar[index + 3]));
                break;
        }
    }


    private void getParsedToDigitOneSymbol(char romeChar) {
        if (Arrays.asList(new Character[]{'I', 'V', 'X'}).contains(romeChar)) {
            switch (romeChar) {
                case 'I':
                    args.add("1");
                    break;
                case 'V':
                    args.add("5");
                    break;
                case 'X':
                    args.add("10");
                    break;
            }
        }
        fillErrorMessage();
    }

    private void getParsedToDigitTwoSymbols(String romeDigits) {
        switch (romeDigits) {
            case "II":
                args.add("2");
                break;
            case "IV":
                args.add("4");
                break;
            case "VI":
                args.add("6");
                break;
            case "IX":
                args.add("9");
                break;
        }
        fillErrorMessage();
    }

    private void getParsedToDigitThreeSymbols(String romeDigits) {
        switch (romeDigits) {
            case "III":
                args.add("3");
                break;
            case "VII":
                args.add("7");
                break;
        }
        fillErrorMessage();
    }

    private void getParsedToDigitFourSymbols(String romeDigits) {
        if (romeDigits.equals("VIII")) {
            args.add("8");
        }
        fillErrorMessage();
    }

    private int getCountSymbols(int side, int operandIndex, int charArrayLen) {
        switch (side) {
            case 0:
                return operandIndex;
            case 1:
                return charArrayLen - operandIndex + 1;
        }
        return -1;
    }

    /*get index of arithmetic operand*/
    private int getOperandIndex(char[] arrayChar) {
        int operandIndex = 0;
        for (int j = 1; j < arrayChar.length; j++) {
            /*checking for operand index*/
            if (Arrays.asList(new Character[]{'-', '+', '/', '*'}).contains(arrayChar[j])) {
                operandIndex = j;
                operand = String.valueOf(arrayChar[j]);
            }
        }
        return operandIndex;
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