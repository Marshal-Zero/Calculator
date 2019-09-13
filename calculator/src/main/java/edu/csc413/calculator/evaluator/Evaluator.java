package edu.csc413.calculator.evaluator;


import edu.csc413.calculator.operators.Open;
import edu.csc413.calculator.operators.Operator;

import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = Operator.allDelimiters();

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval(String expression) {
        String token;

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);


        // filter out spaces
        while (this.tokenizer.hasMoreTokens()) if (!(token = this.tokenizer.nextToken()).equals(" ")) {
            // check if token is an operand
            if (Operand.check(token)) {
                operandStack.push(new Operand(token));
            } else {
                if (!Operator.check(token)) {
                    System.out.println("*****invalid token******");
                    throw new RuntimeException("*****invalid token******");
                }




                // TODO Operator is abstract - these two lines will need to be fixed:
                // The Operator class should contain an instance of a HashMap,
                // and values will be instances of the Operators.  See Operator class
                // skeleton for an example.
                Operator newOperator = Operator.getOperator(token);


                if (Objects.equals(token, "(")) {
                    operatorStack.push(newOperator);
                    continue;
                }
                if (Objects.equals(token, ")")) {

                    if (operatorStack.peek().priority() != 0) {
                        do processOperator(operatorStack.pop());
                        while (operatorStack.peek().priority() != 0);
                    }
                    operatorStack.pop();
                    continue;
                }



                while (!operatorStack.isEmpty() && operatorStack.peek().priority() >= newOperator.priority()) {
                    // note that when we eval the expression 1 - 2 we will
                    // push the 1 then the 2 and then do the subtraction operation
                    // This means that the first number to be popped is the
                    // second operand, not the first operand - see the following code
                    processOperator(operatorStack.pop());
                }

                operatorStack.push(newOperator);
            }
        }

        while (!operatorStack.isEmpty()) {
            processOperator(operatorStack.pop());
        }
        // Control gets here when we've picked up all of the tokens; you must add
        // code to complete the evaluation - consider how the code given here
        // will evaluate the expression 1+2*3
        // When we have no more tokens to scan, the operand stack will contain 1 2
        // and the operator stack will have + * with 2 and * on the top;
        // In order to complete the evaluation we must empty the stacks,
        // that is, we should keep evaluating the operator stack until it is empty;
        // Suggestion: create a method that processes the operator stack until empty.

        //Don't forget to change the return value!
        int expResult = operandStack.pop().getValue();
        return expResult;
    }
    private void processOperator(Operator operator){

        Operand operandTwo = operandStack.pop();
        if (operator.priority() <= 3) {
            operandStack.push(operator.execute(operandStack.pop(), operandTwo));
        } else {
            operandStack.push(operator.execute(operandTwo, null));
        }

    }
    }

