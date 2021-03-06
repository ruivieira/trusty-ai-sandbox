/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.redhat.developer.counterfactual.scoring;

import com.redhat.developer.counterfactual.Measures;
import com.redhat.developer.counterfactual.entities.CreditCardApprovalEntity;
import com.redhat.developer.counterfactual.metrics.Facts;
import com.redhat.developer.counterfactual.models.CreditCardApprovalModel;
import com.redhat.developer.counterfactual.solutions.Approval;
import org.optaplanner.core.api.score.buildin.bendablebigdecimal.BendableBigDecimalScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class CreditCardApprovalBendableScoreCalculator
    extends AbstractCreditCardApprovalScoreCalculator implements EasyScoreCalculator<Approval> {

  private static final Logger logger =
      LoggerFactory.getLogger(CreditCardApprovalBendableScoreCalculator.class);

  @Override
  public BendableBigDecimalScore calculateScore(Approval solution) {

    int primaryHardScore = 0;
    int secondaryHardScore = 0;
    double primarySoftScore = 0.0;

    for (CreditCardApprovalEntity entity : solution.getApprovalsList()) {

      final var predictions = CreditCardApprovalModel.getModel().predict(entity);

      final double inputDistance =
          Math.pow(Measures.manhattan(Facts.input.toArray(), entity.toArray()), 2.0);

      final var output = (Integer) predictions.get("APPROVED");

      if (!output.equals(1)) {
        primaryHardScore -= 1;
      }

      primarySoftScore -= inputDistance;

      if (!entity.getAge().equals(this.getAge())) {
        secondaryHardScore -= 1;
      }
      if (!entity.getChildren().equals(this.getChildren())) {
        secondaryHardScore -= 1;
      }

      logger.debug("Input distance: " + inputDistance);
      logger.debug("Hard Score distance: " + primaryHardScore);
    }

    return BendableBigDecimalScore.of(
        new BigDecimal[] {
          BigDecimal.valueOf(primaryHardScore), BigDecimal.valueOf(secondaryHardScore)
        },
        new BigDecimal[] {BigDecimal.valueOf(primarySoftScore)});
  }
}
