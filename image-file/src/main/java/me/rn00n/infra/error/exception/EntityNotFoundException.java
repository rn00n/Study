package me.rn00n.infra.error.exception;

import me.rn00n.infra.error.ErrorCode;
import org.springframework.validation.Errors;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public EntityNotFoundException(String message, ErrorCode errorCode, Errors errors) {
        super(message, errorCode, errors);
    }
}
