package org.kie.kogito.explainability.local.counterfactual.constraints;

import org.kie.kogito.explainability.model.Output;
import org.kie.kogito.explainability.model.PredictionProvider;
import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.domain.solution.ProblemFactProperty;
import org.optaplanner.core.api.score.buildin.bendablebigdecimal.BendableBigDecimalScore;

import java.math.BigDecimal;
import java.util.List;

import static org.kie.kogito.explainability.local.counterfactual.CounterfactualConfigurationFactory.HARD_LEVELS_SIZE;
import static org.kie.kogito.explainability.local.counterfactual.CounterfactualConfigurationFactory.SOFT_LEVELS_SIZE;

@ConstraintConfiguration
public class CounterfactualConstraintConfiguration {


    public static final String BOOLEAN_DISTANCE = "Boolean feature distance";
    public static final String DOUBLE_DISTANCE ="Double feature distance";
    public static final String PREDICTION_DISTANCE = "Prediction distance";

    @ProblemFactProperty
    private PredictionProvider model;

    @ProblemFactProperty
    private List<Output> goal;

    private CounterfactualConstraintConfiguration() {

    }

    public CounterfactualConstraintConfiguration(PredictionProvider model, List<Output> goal) {
        this.model = model;
        this.goal = goal;
    }

    @ConstraintWeight(PREDICTION_DISTANCE)
    private BendableBigDecimalScore predictionDistance = BendableBigDecimalScore.ofHard(HARD_LEVELS_SIZE, SOFT_LEVELS_SIZE, 0, BigDecimal.ONE);


    @ConstraintWeight(BOOLEAN_DISTANCE)
    private final BendableBigDecimalScore booleanDistance = BendableBigDecimalScore.ofSoft(HARD_LEVELS_SIZE, SOFT_LEVELS_SIZE, 0, BigDecimal.ONE);

    @ConstraintWeight(DOUBLE_DISTANCE)
    private BendableBigDecimalScore doubleDistance = BendableBigDecimalScore.ofSoft(HARD_LEVELS_SIZE, SOFT_LEVELS_SIZE, 0, BigDecimal.ONE);
}