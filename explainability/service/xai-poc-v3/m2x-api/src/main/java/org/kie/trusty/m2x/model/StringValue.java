package org.kie.trusty.m2x.model;

public class StringValue extends Value<String> {

    public StringValue() {
        super("");
    }

    public StringValue(String underlyingObject) {
        super(underlyingObject);
    }
}
