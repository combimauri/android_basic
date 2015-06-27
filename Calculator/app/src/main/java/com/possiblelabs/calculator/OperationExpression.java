package com.possiblelabs.calculator;

/**
 * Created by Mauricio Arce on 26/06/2015.
 */
public abstract class OperationExpression implements Expression {

    private final Expression left;
    private final Expression right;
    private final String operator;

    public OperationExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public float evaluate() {
        switch(operator) {
            case "+" :
                return left.evaluate() + right.evaluate();
            case "-" :
                return left.evaluate() - right.evaluate();
            case "x" :
                return left.evaluate() * right.evaluate();
            case "/" :
                return left.evaluate() / right.evaluate();
            default :
                throw new IllegalArgumentException("Unsupported math operation");
        }
    }
}
