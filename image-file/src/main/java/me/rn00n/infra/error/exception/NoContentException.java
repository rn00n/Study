package me.rn00n.infra.error.exception;

import me.rn00n.infra.error.ErrorCode;
import org.springframework.validation.Errors;

public class NoContentException extends BusinessException {
    public NoContentException(String message) {
        super(message, ErrorCode.NO_CONTENT_EXCEPTION);
    }

    public NoContentException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NoContentException(String message, Errors errors) {
        super(message, ErrorCode.NO_CONTENT_EXCEPTION, errors);
    }

    public NoContentException(String message, ErrorCode errorCode, Errors errors) {
        super(message, errorCode, errors);
    }

}
