package org.kie.kogito.explainability.local.counterfactual.constraints;

import org.kie.kogito.explainability.local.counterfactual.entities.BooleanEntity;
import org.kie.kogito.explainability.local.counterfactual.entities.CategoricalEntity;
import org.kie.kogito.explainability.local.counterfactual.entities.DoubleEntity;
import org.kie.kogito.explainability.local.counterfactual.entities.IntegerEntity;
import org.kie.kogito.explainability.model.*;
import org.optaplanner.core.api.domain.solution.ProblemFactProperty;
import org.optaplanner.core.api.score.buildin.bendablebigdecimal.BendableBigDecimalScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.kie.kogito.explainability.local.counterfactual.constraints.CounterfactualConstraintConfiguration.*;

public class CounterfactualContraintsProvider implements ConstraintProvider {

    private static final Logger logger =
            LoggerFactory.getLogger(CounterfactualContraintsProvider.class);

    @ProblemFactProperty
    private PredictionProvider model;

    @ProblemFactProperty
    private List<Output> goal;

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                booleanDistance(constraintFactory),
                doubleDistance(constraintFactory),
                predictionDistance(constraintFactory)
        };
    }

    private Constraint predictionDistance(ConstraintFactory constraintFactory) {
        return constraintFactory.from(DoubleEntity.class).join(IntegerEntity.class)
                .groupBy(((doubleEntity, integerEntity) -> {
                    List<Feature> features = new ArrayList<>();
                    features.add(doubleEntity.asFeature());
                    features.add(integerEntity.asFeature());
                    return features;
                })).join(BooleanEntity.class).groupBy(((features, booleanEntity) -> {
                    features.add(booleanEntity.asFeature());
                    return features;
                })).join(CategoricalEntity.class).groupBy((features, categoricalEntity) -> {
                    features.add(categoricalEntity.asFeature());
                    return features;
                }).penalizeBigDecimal(PREDICTION_DISTANCE, BendableBigDecimalScore.ofHard(3, 1, 0, BigDecimal.valueOf(1)),
                        features -> {
                            PredictionInput predictionInput = new PredictionInput(features);

                            List<PredictionInput> inputs = List.of(predictionInput);

                            CompletableFuture<List<PredictionOutput>> predictionAsync = model.predictAsync(inputs);

                            List<PredictionOutput> predictions = null;
                            double distance = 0.0;
                            try {
                                predictions = predictionAsync.get();


                                for (PredictionOutput predictionOutput : predictions) {

                                    final List<Output> outputs = predictionOutput.getOutputs();

                                    if (outputs.size() != predictions.size()) {
                                        throw new IllegalArgumentException("Prediction size must be equal to goal size");
                                    }
                                    for (int i = 0; i < outputs.size(); i++) {
                                        final Output output = outputs.get(i);
                                        final Output goalOutput = goal.get(i);
                                        distance += goalOutput.getValue().asNumber() - output.getValue().asNumber();
                                    }
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            return BigDecimal.valueOf(distance);
                        });
    }

    private Constraint booleanDistance(ConstraintFactory constraintFactory) {
        return constraintFactory.from(BooleanEntity.class)
                .penalizeBigDecimal(BOOLEAN_DISTANCE,
                        BendableBigDecimalScore.ofSoft(3, 1, 0, BigDecimal.valueOf(1))
                        , booleanEntity -> BigDecimal.valueOf(booleanEntity.distance()));

    }

    private Constraint doubleDistance(ConstraintFactory constraintFactory) {
        return constraintFactory.from(DoubleEntity.class)
                .penalizeBigDecimal(DOUBLE_DISTANCE,
                        BendableBigDecimalScore.ofSoft(3, 1, 0, BigDecimal.valueOf(1))
                        , doubleEntity -> BigDecimal.valueOf(doubleEntity.distance()));
    }

}