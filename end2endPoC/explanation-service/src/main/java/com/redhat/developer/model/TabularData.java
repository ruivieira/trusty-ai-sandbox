package com.redhat.developer.model;

public class TabularData {

    private final double[] x;
    private final double[] y;
    private final Feature feature;

    public TabularData(Feature feature, double[] x, double[] y) {
        assert x.length == y.length : "x and y lenghts do not match";
        this.feature = feature;
        this.x = x;
        this.y = y;
    }

    public Feature getFeature() {
        return feature;
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }
}
