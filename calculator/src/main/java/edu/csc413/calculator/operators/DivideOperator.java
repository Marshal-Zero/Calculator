package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class DivideOperator extends Operator {
   public int priority() {
       return 2;
   }
   public Operand execute (Operand operandOne, Operand operandTwo){
       var solution = new Operand (operandOne.getValue() / operandTwo.getValue());
       return solution;
   }
}
