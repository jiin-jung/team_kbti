package kleague.kbti.exception;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String domain,
        String code,
        String message,
        String path,
        List<FieldErrorResponse> fieldErrors
) {

    public static ErrorResponse of(int status, String code, String message, String path) {
        return new ErrorResponse(Instant.now(), status, null, code, message, path, List.of());
    }

    public static ErrorResponse of(int status, String domain, String code, String message, String path) {
        return new ErrorResponse(Instant.now(), status, domain, code, message, path, List.of());
    }

    public static ErrorResponse of(
            int status,
            String domain,
            String code,
            String message,
            String path,
            List<FieldErrorResponse> fieldErrors
    ) {
        return new ErrorResponse(Instant.now(), status, domain, code, message, path, fieldErrors);
    }
}
