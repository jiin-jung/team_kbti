package kleague.kbti.exception.code;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    ErrorDomain domain();

    String code();

    String message();

    HttpStatus status();
}
