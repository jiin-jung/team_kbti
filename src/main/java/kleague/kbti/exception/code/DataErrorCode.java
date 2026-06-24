package kleague.kbti.exception.code;

import org.springframework.http.HttpStatus;

public enum DataErrorCode implements ErrorCode {
    TEAM_TACTICS_CSV_LOAD_FAILED("DATA-500-001", "팀 전술 데이터를 불러오지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    TEAM_RANKING_CSV_LOAD_FAILED("DATA-500-002", "팀 순위 데이터를 불러오지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    PLAYER_RATINGS_CSV_LOAD_FAILED("DATA-500-003", "선수 평점 데이터를 불러오지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    CSV_MIGRATION_FAILED("DATA-500-004", "K리그 데이터 마이그레이션에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    CSV_RESOURCE_NOT_FOUND("DATA-500-005", "CSV 리소스를 찾을 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    DataErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public ErrorDomain domain() {
        return ErrorDomain.DATA;
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
