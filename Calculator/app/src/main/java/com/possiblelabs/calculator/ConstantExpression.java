package com.possiblelabs.calculator;

/**
 * Created by Mauricio Arce on 26/06/2015.
 */
public class ConstantExpression implements Expression{

    private float value;

    public ConstantExpression(float value) {
        this.value = value;
    }

    @Override
    public float evaluate() {
        return value;
    }
}
