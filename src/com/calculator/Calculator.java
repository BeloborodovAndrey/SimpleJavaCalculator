package com.calculator;

/**
 * @author belob
 * Class that implements the basic functionality of arithmetic calculator operations
 */
public class Calculator implements ArithmeticOperations{
    /**
     * method for selecting requirement operation
     * @param operation determines the operation that will be selected from ArithmeticOperations
     */
    public void operationSelection(int a, int b, char operation){
        switch (operation){
            case ('+'):
                System.out.println(composition(a,b));
                break;
            case ('-'):
                System.out.println(subtraction(a,b));
                break;
            case ('/'):
                System.out.println(division(a,b));
                break;
            case ('*'):
                System.out.println(multiplication(a,b));
                break;
        }
    }
}
