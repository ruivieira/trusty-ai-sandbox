package org.kie.trusty.m2x.model;

public class DoubleValue extends Value<Double> {

    public DoubleValue() {
        super(0d);
    }

    public DoubleValue(Double underlyingObject) {
        super(underlyingObject);
    }
}
