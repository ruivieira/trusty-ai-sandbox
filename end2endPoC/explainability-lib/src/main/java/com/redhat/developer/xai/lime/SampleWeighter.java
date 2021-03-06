package com.redhat.developer.xai.lime;

import java.util.Arrays;
import java.util.Collection;

import com.redhat.developer.model.PredictionInput;
import com.redhat.developer.utils.DataUtils;
import org.apache.commons.lang3.tuple.Pair;

class SampleWeighter {

    static double[] getSampleWeights(PredictionInput targetInput, Collection<Pair<double[], Double>> training) {
        int noOfFeatures = targetInput.getFeatures().size();
        double[] x = new double[noOfFeatures];
        Arrays.fill(x, 1);

        return training.stream().map(Pair::getLeft).map(
                d -> DataUtils.euclideanDistance(x, d)).map(d -> DataUtils.exponentialSmoothingKernel(d, 0.75 *
                Math.sqrt(noOfFeatures))).mapToDouble(Double::doubleValue).toArray();
    }
}
