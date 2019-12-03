package com.calculator;

import java.util.Scanner;

/**
 * @author belob
 * @version 1.0
 * main executing project class
 */
public class MainCalculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter operation: \n");
        calculator.operationsParser.parsingString(scanner.next());
        calculator.calculateResult();
        System.out.println("Result: " + calculator.calculateResult());
    }
}
