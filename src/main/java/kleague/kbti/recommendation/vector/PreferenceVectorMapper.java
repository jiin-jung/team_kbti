package kleague.kbti.recommendation.vector;

import kleague.kbti.dto.request.KbtiRequest;
import kleague.kbti.model.TacticalVector;

public interface PreferenceVectorMapper {

    TacticalVector map(KbtiRequest request);
}
