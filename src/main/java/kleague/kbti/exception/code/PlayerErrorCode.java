package kleague.kbti.exception.code;

import org.springframework.http.HttpStatus;

public enum PlayerErrorCode implements ErrorCode {
    PLAYER_DATA_EMPTY("PLAYER-500-001", "선수 데이터가 초기화되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;

    PlayerErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    @Override
    public ErrorDomain domain() {
        return ErrorDomain.PLAYER;
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
