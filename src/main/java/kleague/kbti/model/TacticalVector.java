package kleague.kbti.model;

import kleague.kbti.exception.code.RecommendationErrorCode;
import kleague.kbti.exception.domain.RecommendationException;

import java.util.Arrays;

public record TacticalVector(double tempo, double directness, double pressing, double sideUsage, double fight) {

    public double[] values() {
        return new double[]{tempo, directness, pressing, sideUsage, fight};
    }

    public double distanceTo(TacticalVector other) {
        double[] current = values();
        double[] target = other.values();

        if (current.length != target.length) {
            throw new RecommendationException(RecommendationErrorCode.VECTOR_DIMENSION_MISMATCH);
        }

        double sum = 0;
        for (int i = 0; i < current.length; i++) {
            double diff = current[i] - target[i];
            sum += diff * diff;
        }
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        return Arrays.toString(values());
    }
}
