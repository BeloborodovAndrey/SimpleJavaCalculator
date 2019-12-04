package com.calculator;

import java.util.Arrays;

/**
 * @author belob
 * class for implementation digits operations
 */

public class RomeOperationsParser extends OperationsParser {

    public RomeOperationsParser(){
        super();
    }
    /**
     * working with rome digit arithmetic operation
     *
     * @param arrayChar operation string
     */
     void romeDigitsOperationAnalyze(char[] arrayChar) {
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
}
