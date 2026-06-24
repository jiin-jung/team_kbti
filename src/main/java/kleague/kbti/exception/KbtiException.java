package kleague.kbti.exception;

import kleague.kbti.exception.code.ErrorCode;

public class KbtiException extends RuntimeException {

    private final ErrorCode errorCode;

    public KbtiException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public KbtiException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public KbtiException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.message(), cause);
        this.errorCode = errorCode;
    }

    public KbtiException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
