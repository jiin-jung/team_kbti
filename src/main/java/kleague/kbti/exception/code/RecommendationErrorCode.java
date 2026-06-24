package kleague.kbti.exception.code;

import org.springframework.http.HttpStatus;

public enum RecommendationErrorCode implements ErrorCode {
    TEAM_TACTICS_EMPTY("RECOMMENDATION-500-001", "팀 전술 데이터가 초기화되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    VECTOR_DIMENSION_MISMATCH("RECOMMENDATION-500-002", "전술 벡터 차원이 일치하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    RecommendationErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public ErrorDomain domain() {
        return ErrorDomain.RECOMMENDATION;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus status() {
        return status;
    }
}
