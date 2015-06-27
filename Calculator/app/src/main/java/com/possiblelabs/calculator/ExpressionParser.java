package com.possiblelabs.calculator;

/**
 * Created by Mauricio Arce on 26/06/2015.
 */
public class ExpressionParser {

    public Expression parseExpression(String expression) {
        Expression result = parseConstant(expression);

        if (result != null) {
            return result;
        }

        String operator = searchOperator(expression);
        int opPosition = expression.indexOf(operator);
        Expression left = parseExpression(expression.substring(0, opPosition));
        Expression right = parseExpression(expression.substring(opPosition + 1));
        return createOperation(operator, left, right);
    }

    private Expression createOperation(String operator, Expression left, Expression right) {
        switch(operator) {
            case "+" :
                return new PlusExpression(left, right);
            case "-" :
                return new MinusExpression(left, right);
            case "x" :
                return new MultiplyExpression(left, right);
            case "/" :
                return new DivideExpression(left, right);
            default :
                throw new IllegalArgumentException("Unsupported math operation");
        }
    }

    private String searchOperator(String expression) {
        if (expression.contains("+")) {
            return "+";
        } else if (expression.contains("-")) {
            return "-";
        } else if (expression.contains("x")) {
            return "x";
        } else {
            return "/";
        }
    }

    private Expression parseConstant(String expression) {
        try {
            float parseFloat = Float.parseFloat(expression);
            return new ConstantExpression(parseFloat);
        } catch (NumberFormatException error) {
            return null;
        }
    }

}
