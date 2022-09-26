package com.pluralsight.calcengine;

import java.util.Scanner;

// import org.jcp.xml.dsig.internal.dom.DOMKeyInfoFactory;

import java.time.LocalDate;


public class newApp {
    public static void main(String[] args) {
        double[] leftVals = {100.0d, 25.0d, 225.0d, 11.0d};
        double[] rightVals = {50.0d, 92.0d, 17.0d, 3.0d};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

    if(args.length == 0){
        // for(int i = 0; i < opCodes; i++){
        //     results[i] = execute(opCodes[i], leftVals[i]);
        // } 
        for(double currentResult : results)
            System.out.println(currentResult);
    } else if (args.length == 1 && args[0].equals("interactive"))
        executeInteractively();
    else if (args.length == 3)
        handleCommandLine(args);
    else
        System.out.println("provide operator and 2 arguments");
}

    static void executeInteractively(){
        System.out.println("Enter an operator and 2 numbers");
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ");
            performOperation(parts);
        }
    }

    private static void performOperation(String[] parts) {
        char opCode = opCodeFromString(parts[0]);
        if(opCode == 'w')
            handleWhen(parts);
        else {
        double leftVal = valueFromWord(parts[1]);
        double rightVal = valueFromWord(parts[2]);
        double result = execute(opCode,leftVal,rightVal);
        displayResult(opCode, leftVal,rightVal,result);
        }
    }

    private static void handleWhen(String[] parts) {
        LocalDate startDate = LocalDate.parse(parts[1]);
        long daysToAdd = (long) valueFromWord(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);
        String output = String.format("%s plus %d days is %s",startDate, daysToAdd, newDate);
        System.out.println(output);
    }

    private static void displayResult(char opCode, double leftVal, double rightVal, double result){
        char symbol = symbolFromOpCode(opCode);

        String output = String.format("%f %c %f = %f", leftVal,symbol,rightVal,result);
        System.out.println(output);
    }

    private static char symbolFromOpCode(char opCode){
        char[] opCodes = {'a','s','m','d'};
        char[] symbols = {'+','-','*','/'};
        char symbol = ' ';
        for(int index = 0; index < opCodes.length; index++){
            if(opCode == opCodes[index]){
                symbol = symbols[index];
                break;
            }
        }
        return symbol;
    }

    private static void handleCommandLine(String[] args){
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

    static char opCodeFromString(String operationName){
        char opCode = operationName.charAt(0);
        return opCode;
    }

    static double valueFromWord(String word){
        String[] numberWords = {
            "zero", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine"
        };
        double value = -1d;
        for(int index = 0; index < numberWords.length; index++){
            if(word.equals(numberWords[index])){
                value = index;
                break;
            }
        }
        if(value == -1d)
           value = Double.parseDouble(word);
        return value;
    }
}    