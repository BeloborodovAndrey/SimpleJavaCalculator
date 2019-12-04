package com.calculator;

import java.util.Scanner;

/**
 * @author belob
 * @version 1.0
 * main executing project class
 */
public class MainCalculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter operation: \n");
            calculator.operationsParser.parsingString(scanner.next());
            System.out.println("Result: " + calculator.calculateResult());
            if(calculator.errMessage != ""){
                throw new Exception(calculator.errMessage);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
