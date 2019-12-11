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
    /*arguments of operations*/
    List args;
    /*wrong operations flag*/
    private String errorMessage = "";
    /*arithmetic operand*/
    String operand;


    public OperationsParser() {
        /*initialize allowableValuesList*/
        Collections.addAll(allowableValuesList, allowableValues);
        args = new List();
    }

    /**
     * @param operationString user input string operation
     */
    public void parsingString(String operationString) {
        errorMessage = "";
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
            ArabDigitsOperationParser parser = new ArabDigitsOperationParser();
            if (!parser.digitOperationAnalyze(arrayChar)){
                fillErrorMessage();
                return;
            }
            if (parser.args != null){
                this.args = parser.args;
                this.operand = parser.operand;
            }
            return;
        }
        /*check for rome digits*/
        if (Arrays.asList(new Character[]{'I', 'X', 'V'}).contains(arrayChar[0])) {
            RomeOperationsParser parser = new RomeOperationsParser();
            if (!parser.romeDigitsOperationAnalyze(arrayChar)){
                fillErrorMessage();
                return;
            }
            if (parser.args != null){
                this.args = parser.args;
                this.operand = parser.operand;
            }
            return;
        }
        fillErrorMessage();
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

    int getCountSymbols(int side, int operandIndex, int charArrayLen) {
        switch (side) {
            case 0:
                return operandIndex;
            case 1:
                return charArrayLen - (operandIndex + 1);
        }
        return -1;
    }

    /*get index of arithmetic operand*/
    int getOperandIndex(char[] arrayChar) {
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
}
