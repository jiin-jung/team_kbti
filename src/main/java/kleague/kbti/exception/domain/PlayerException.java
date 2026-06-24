package kleague.kbti.exception.domain;

import kleague.kbti.exception.KbtiException;
import kleague.kbti.exception.code.PlayerErrorCode;

public class PlayerException extends KbtiException {

    public PlayerException(PlayerErrorCode errorCode) {
        super(errorCode);
    }
}
