package me.rn00n.infra.error.exception;

import me.rn00n.infra.error.ErrorCode;

public class DoNotExistException extends BusinessException {
    public DoNotExistException(String value) {
        super(value, ErrorCode.DO_NOT_EXIST_EXCEPTION);
    }

    public DoNotExistException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
