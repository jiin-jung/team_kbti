package kleague.kbti.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import kleague.kbti.exception.code.CommonErrorCode;
import kleague.kbti.exception.code.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        List<FieldErrorResponse> fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorResponse(error.getField(), error.getDefaultMessage()))
                .toList();

        ErrorResponse response = ErrorResponse.of(
                CommonErrorCode.INVALID_REQUEST.status().value(),
                CommonErrorCode.INVALID_REQUEST.domain().name(),
                CommonErrorCode.INVALID_REQUEST.code(),
                CommonErrorCode.INVALID_REQUEST.message(),
                request.getRequestURI(),
                fieldErrors
        );
        return ResponseEntity.status(CommonErrorCode.INVALID_REQUEST.status()).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException exception,
            HttpServletRequest request
    ) {
        List<FieldErrorResponse> fieldErrors = exception.getConstraintViolations()
                .stream()
                .map(violation -> new FieldErrorResponse(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()
                ))
                .toList();

        ErrorResponse response = ErrorResponse.of(
                CommonErrorCode.INVALID_REQUEST.status().value(),
                CommonErrorCode.INVALID_REQUEST.domain().name(),
                CommonErrorCode.INVALID_REQUEST.code(),
                CommonErrorCode.INVALID_REQUEST.message(),
                request.getRequestURI(),
                fieldErrors
        );
        return ResponseEntity.status(CommonErrorCode.INVALID_REQUEST.status()).body(response);
    }

    @ExceptionHandler(KbtiException.class)
    public ResponseEntity<ErrorResponse> handleKbtiException(KbtiException exception, HttpServletRequest request) {
        ErrorCode errorCode = exception.getErrorCode();
        if (errorCode.status().is5xxServerError()) {
            log.error("Domain error occurred. domain={}, code={}", errorCode.domain(), errorCode.code(), exception);
        }

        ErrorResponse response = ErrorResponse.of(
                errorCode.status().value(),
                errorCode.domain().name(),
                errorCode.code(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(errorCode.status()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception exception, HttpServletRequest request) {
        log.error("Unexpected API error", exception);
        ErrorResponse response = ErrorResponse.of(
                CommonErrorCode.INTERNAL_SERVER_ERROR.status().value(),
                CommonErrorCode.INTERNAL_SERVER_ERROR.domain().name(),
                CommonErrorCode.INTERNAL_SERVER_ERROR.code(),
                CommonErrorCode.INTERNAL_SERVER_ERROR.message(),
                request.getRequestURI()
        );
        return ResponseEntity.status(CommonErrorCode.INTERNAL_SERVER_ERROR.status()).body(response);
    }
}
