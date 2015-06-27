package com.possiblelabs.calculator;

/**
 * Created by Mauricio Arce on 26/06/2015.
 */
public class PlusExpression extends OperationExpression {

    public PlusExpression(Expression left, Expression right) {
        super(left, right, "+");
    }

}
