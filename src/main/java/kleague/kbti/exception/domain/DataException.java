package kleague.kbti.exception.domain;

import kleague.kbti.exception.KbtiException;
import kleague.kbti.exception.code.DataErrorCode;

public class DataException extends KbtiException {

    public DataException(DataErrorCode errorCode) {
        super(errorCode);
    }

    public DataException(DataErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public DataException(DataErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DataException(DataErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
