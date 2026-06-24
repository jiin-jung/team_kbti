package kleague.kbti.recommendation.vector;

import kleague.kbti.dto.request.KbtiRequest;
import kleague.kbti.model.TacticalVector;
import org.springframework.stereotype.Component;

@Component
public class ScaledPreferenceVectorMapper implements PreferenceVectorMapper {

    private static final double SCORE_SCALE = 20.0;
    private static final double DEFAULT_SIDE_USAGE = 50.0;

    @Override
    public TacticalVector map(KbtiRequest request) {
        return new TacticalVector(
                request.getTempo() * SCORE_SCALE,
                request.getDirectness() * SCORE_SCALE,
                request.getPressing() * SCORE_SCALE,
                DEFAULT_SIDE_USAGE,
                request.getFight() * SCORE_SCALE
        );
    }
}
