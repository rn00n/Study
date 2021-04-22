package me.rn00n.infra.error.exception;

import me.rn00n.infra.error.ErrorCode;

public class AlreadyExistException extends BusinessException {
    public AlreadyExistException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public AlreadyExistException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
