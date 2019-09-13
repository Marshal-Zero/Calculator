package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class AddOperator extends Operator {
    public int priority() {
        return 1;
    }

    public Operand execute( Operand operandOne, Operand operandTwo ) {
        var solution = new Operand(operandOne.getValue() + operandTwo.getValue());
        return solution;
    }
}
