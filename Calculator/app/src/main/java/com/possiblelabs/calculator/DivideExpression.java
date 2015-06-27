package com.possiblelabs.calculator;

/**
 * Created by Mauricio Arce on 26/06/2015.
 */
public class DivideExpression extends OperationExpression {

    public DivideExpression(Expression left, Expression right) {
        super(left, right, "/");
    }

    @Override
    public float evaluate() {
        if (getRight().evaluate() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return super.evaluate();
    }

}
