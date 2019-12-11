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
            System.out.println("For exit of program enter 'exit' \n ");
            while (true) {
                System.out.println("Enter operation: \n");
                String operation = scanner.next();
                if (operation.equalsIgnoreCase("exit")){
                    return;
                }
                System.out.println("Result: " + calculator.calculateResult(operation));
                if (calculator.errMessage != "") {
                    throw new Exception(calculator.errMessage);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
