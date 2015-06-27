package com.possiblelabs.calculator;

/**
 * Created by Mauricio Arce on 26/06/2015.
 */
public class MultiplyExpression extends OperationExpression {

    public MultiplyExpression(Expression left, Expression right) {
        super(left, right, "x");
    }

}
