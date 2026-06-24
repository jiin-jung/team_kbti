package kleague.kbti.exception.domain;

import kleague.kbti.exception.KbtiException;
import kleague.kbti.exception.code.TeamErrorCode;

public class TeamException extends KbtiException {

    public TeamException(TeamErrorCode errorCode) {
        super(errorCode);
    }

    public TeamException(TeamErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
