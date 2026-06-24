package kleague.kbti.exception.code;

import org.springframework.http.HttpStatus;

public enum TeamErrorCode implements ErrorCode {
    TEAM_NOT_FOUND("TEAM-404-001", "존재하지 않는 팀입니다.", HttpStatus.NOT_FOUND),
    TEAM_RANKING_MISMATCH("TEAM-500-001", "팀 순위 데이터와 전술 데이터가 일치하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    TeamErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public ErrorDomain domain() {
        return ErrorDomain.TEAM;
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
