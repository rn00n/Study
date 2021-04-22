package me.rn00n.infra.error.exception;

import me.rn00n.infra.error.ErrorCode;
import org.springframework.validation.Errors;

public class BadRequestException extends BusinessException {
    public BadRequestException(String message) {
        super(message, ErrorCode.BAD_REQUEST_EXCEPTION);
    }

    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public BadRequestException(String message, Errors errors) {
        super(message, ErrorCode.BAD_REQUEST_EXCEPTION, errors);
    }

    public BadRequestException(String message, ErrorCode errorCode, Errors errors) {
        super(message, errorCode, errors);
    }

}
