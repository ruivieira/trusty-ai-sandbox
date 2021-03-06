package org.kie.trusty.m2x.model;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The input of a prediction
 */
@Schema(name = "PredictionInput")
public class PredictionInput {

    private List<Feature> features;

    public PredictionInput() {
        this.features = Collections.emptyList();
    }

    @JsonCreator
    public PredictionInput(@JsonProperty("features") List<Feature> features) {
        this.features = features;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "PredictionInput{" +
                "features=" + features +
                '}';
    }
}
