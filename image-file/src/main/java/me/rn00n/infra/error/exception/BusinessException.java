package me.rn00n.infra.error.exception;

import me.rn00n.infra.error.ErrorCode;
import org.springframework.validation.Errors;

public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;
    private Errors errors;

    public BusinessException(String message, ErrorCode errorCode, Errors errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Errors getErrors() {
        return errors;
    }
}
