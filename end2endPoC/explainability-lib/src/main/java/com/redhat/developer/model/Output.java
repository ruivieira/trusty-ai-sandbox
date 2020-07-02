package com.redhat.developer.model;

/**
 * a single output value generated by a {@link Model} and incorporated in a {@link PredictionOutput}.
 */
public class Output {

    private final Value value;
    private final Type type;
    private final double score;
    private final String name;

    public Output(String name, Type type, Value value, double score) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.score = score;
    }

    /**
     * get the score (confidence) associated to this output
     * @return the score
     */
    public double getScore() {
        return score;
    }

    /**
     * get the output type
     * @return the output type
     */
    public Type getType() {
        return type;
    }

    /**
     * get the value associated to this output
     * @return the output value
     */
    public Value getValue() {
        return value;
    }
}