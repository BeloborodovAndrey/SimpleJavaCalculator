package com.calculator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * @author belob
 * Class that implements the basic functionality of arithmetic calculator operations
 */
public class Calculator implements ArithmeticOperations {

    /*array of possible input string values*/
    String[] allowableValues = new String[]
            {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "+", "-", "/", "*"};

    /*list for comfortable working with  allowableValues*/
    ArrayList<String> allowableValuesList = new ArrayList<String>();

    /*arguments of operations*/
    private List args;

    /*arithmetic operation*/
    private String operation;

    /*string for error cases*/
    String errorMessage = "";

    /*class for parsing operations*/
    public OperationsParser operationsParser;
    public Calculator(){
        /*initialize allowableValuesList*/
        Collections.addAll(allowableValuesList, allowableValues);
        /*complement allowableValuesList integer values*/
        for (int i = 0; i < 11 ; i++) {
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
        String result = operationSelection(Integer.valueOf(args.getItem(0)),
                Integer.valueOf(args.getItem(1)), operation);
        if (result == null){
            return "illegal operation";
        }
        return result;
    }

    /**
     * inner class for parsing operations strings
     */
    public class OperationsParser {
        /**
         * @param operationString user input string operation
         */
        public void parsingString(String operationString) {
            if (operationString == ""){
                errorMessage = "It's void string, try again";
                return;
            }
            /*delete all spaces*/
            String tempString = operationString.replaceAll(" ","");
            /*parser*/
            for (int i = 0; i < tempString.length() ; i++) {
                a
            }

        }
    }
}
