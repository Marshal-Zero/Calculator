package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
    private int operandInString;
    /**
     * construct operand from string token.
     */
    public Operand(String token) {
        operandInString =Integer.parseInt(token);

    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        operandInString = value;

    }

    /**
     * return value of operand
     */
    public int getValue() {
        return operandInString;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        try {
            Integer.parseInt(token);
        } catch(NumberFormatException e){
                return false;
            }
        return true;
        }


    }

