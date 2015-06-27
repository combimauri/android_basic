package com.possiblelabs.calculator;

/**
 * Created by Mauricio Arce on 26/06/2015.
 */
public class MinusExpression extends OperationExpression {

    public MinusExpression(Expression left, Expression right) {
        super(left, right, "-");
    }

}
