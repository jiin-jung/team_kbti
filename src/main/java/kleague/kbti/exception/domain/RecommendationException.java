package kleague.kbti.exception.domain;

import kleague.kbti.exception.KbtiException;
import kleague.kbti.exception.code.RecommendationErrorCode;

public class RecommendationException extends KbtiException {

    public RecommendationException(RecommendationErrorCode errorCode) {
        super(errorCode);
    }

    public RecommendationException(RecommendationErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
