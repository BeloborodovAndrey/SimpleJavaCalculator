package com.calculator;

/**
 * @author belob
 * class for implementation digits operations
 */
public class ArabDigitsOperationParser extends OperationsParser {

    public ArabDigitsOperationParser(){
        super();
    }
    /**
     * working with digit arithmetic operation
     *
     * @param arrayChar operation string
     */
     void digitOperationAnalyze(char[] arrayChar) {
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
