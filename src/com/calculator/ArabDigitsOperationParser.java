package com.calculator;

import java.awt.*;


/**
 * @author belob
 * class for implementation digits operations
 */
public class ArabDigitsOperationParser extends OperationsParser {

    List args;
    String operand;
    public ArabDigitsOperationParser(){
        super();
        args = new List();
    }

    /**
     * working with digit arithmetic operation
     *
     * @param arrayChar operation string
     * @return
     */
     boolean digitOperationAnalyze(char[] arrayChar) {
        /*digit operation string length can't be more then 5*/
        if (arrayChar.length > 5 || arrayChar.length < 3) {
            fillErrorMessage();
            return false;
        }
        /* get the index of arithmetic operand */
        int operandIndex = getOperandIndex(arrayChar);
        if (operandIndex < 1 || operandIndex > 2) {
            fillErrorMessage();
            return false;
        }
        operand = String.valueOf(arrayChar[operandIndex]);
        /*fill first argument*/
        if (!fillFirstArgument(operandIndex, arrayChar) ||
                (!fillSecondArgument(operandIndex, arrayChar))) {
            fillErrorMessage();
            return false;
        }
         return true;
     }
    /*fill first argument*/
    private boolean fillFirstArgument(int operandIndex, char[] arrayChar) {
        if (operandIndex == 2) {
            return checkForTwoSymbols(operandIndex, arrayChar);
        }
        args.add(String.valueOf(arrayChar[operandIndex - 1]));
        return true;
    }

    private boolean checkForTwoSymbols(int Index, char[] arrayChar) {
        if (!Character.isDigit(arrayChar[Index - 1])) {
            return false;
        }
        int argument = Integer.parseInt(String.valueOf(arrayChar[Index - 2]) +
                String.valueOf(arrayChar[Index - 1]));
        if (argument != 10) {
            return false;
        }
        args.add(String.valueOf(argument));
        return true;
    }

    private boolean fillSecondArgument(int operandIndex, char[] arrayChar) {
        if (arrayChar.length == 5) {
            return checkForTwoSymbols(arrayChar.length, arrayChar);
        }
        if (arrayChar.length == 4) {
            switch (operandIndex) {
                case 1:
                    return checkForTwoSymbols(arrayChar.length, arrayChar);
                case 2:
                    return getOneSymbol(arrayChar.length, arrayChar);
            }
            return false;
        }
        if (arrayChar.length == 3) {
                    return getOneSymbol(arrayChar.length, arrayChar);
            }
        return false;
    }

    private boolean getOneSymbol(int operandIndex, char[] arrayChar) {
        if (Character.isDigit(arrayChar[operandIndex - 1])) {
            args.add(String.valueOf(arrayChar[operandIndex - 1]));
            return true;
        } else {
            return false;
        }
    }
}
