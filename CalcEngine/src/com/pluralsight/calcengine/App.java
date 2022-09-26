package com.pluralsight.calcengine;

import java.util.Scanner;

import java.time.LocalDate;



public class App {

    public static void main(String[] args) {
        double[] leftVals = {100.0d, 25.0d, 225.0d, 11.0d};
        double[] rightVals = {50.0d, 92.0d, 17.0d, 3.0d};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        if (args.length == 0) {
            for (int i = 0; i < opCodes.length; i++) {
                results[i] = execute(opCodes[i], leftVals[i], rightVals[i]);
            }
            for (double currentResult : results)
                System.out.println(currentResult);
        } else if(args.length == 1 && args[0].equals("interactive"))
            executeInteractively();
        else if(args.length == 3)
            performOperation(args);
        else
            System.out.println("Please provide an operation code and 2 numeric values");
    }

    static void executeInteractively() {
        System.out.println("Enter an operation and two numbers:");
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ");
            performOperation(parts);
        }
    }

    private static void performOperation(String[] parts) {
        char opCode = opCodeFromString(parts[0]);
            if(opCode == 'w')
                handleThis(parts);
            else{
        double leftVal = Double.parseDouble(parts[1]);
        double rightVal = Double.parseDouble(parts[2]);
        double result = execute(opCode, leftVal, rightVal);
        displayResult(opCode, leftVal, rightVal, result);
    }
    }

    private static void handleThis (String[] item){
        LocalDate startingDate = LocalDate.parse(item[1]);
        long additionalDays = (long) valueFromWord(item[2]);
        LocalDate newDay = startingDate.plusDays(additionalDays);
        String response = String.format("%s plus %d days is %s", startingDate, additionalDays, newDay);
        System.out.println(response);
    }



    private static void displayResult(char opCode, double leftVal, double rightVal, double result) {
        char symbol = symbolFromOpCode(opCode);
    StringBuilder builder = new StringBuilder(20);
       builder.append(leftVal);
       builder.append(" ");
       builder.append(symbol);
       builder.append(" ");
       builder.append(rightVal);
       builder.append(" = ");
       builder.append(result);
     String output = builder.toString();

    //  String output = String.format("%.3f %c %.3f = %.3f", leftVal, symbol, rightVal, result);
     System.out.println(output);
    }

    private static char symbolFromOpCode(char opCode) {
        char[] opCodes = {'a', 's', 'm', 'd'};
        char[] symbols = {'+', '-', '*', '/'};
        char symbol = ' ';
        for(int index = 0; index < opCodes.length; index++) {
            if(opCode == opCodes[index]) {
                symbol = symbols[index];
                break;
            }
        }

        return symbol;
    }

    private static void handleCommandLine(String[] args) {
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);
        double result = execute(opCode, leftVal, rightVal);
        System.out.println(result);
    }

    static double execute(char opCode, double leftVal, double rightVal) {
        double result;
        switch (opCode) {
            case 'a':
                result = leftVal + rightVal;
                break;
            case 's':
                result = leftVal - rightVal;
                break;
            case 'm':
                result = leftVal * rightVal;
                break;
            case 'd':
                result = rightVal != 0 ? leftVal / rightVal : 0.0d;
                break;
            default:
                System.out.println("Invalid opCode: " + opCode);
                result = 0.0d;
                break;
        }
        return result;
    }

    static char opCodeFromString(String operationName) {
        char opCode = operationName.charAt(0);
        return opCode;
    }

    static double valueFromWord(String word) {

        double selection = 0d; 

        switch (word) {
            case "zero":
                selection = 0.0d;
                break;
            case "one":
                selection = 1.0d;
                break;
            case "two":
                selection = 2.0d;
                break;
            case "three":
                selection = 3.0d;
                break;
            case "four":
                selection = 4.0d;
                break;
            case "five":
                selection = 5.0d;
                break;
            case "six":
                selection = 6.0d;
                break;
            case "seven":
                selection = 7.0d;
                break;
            case "eight":
                selection = 8.0d;
                break;
            case "nine":
                selection = 9.0d;
                break;
            default:
                System.out.println("Invalid word: " + word);
                selection = 0.0d;
                break;
        }
        return selection;
    }
}

