package me.rn00n.infra.error;

import lombok.extern.slf4j.Slf4j;
import me.rn00n.infra.error.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        final ErrorCode errorCode = e.getErrorCode();
        final Errors errors = e.getErrors();
        final ErrorResponse response = ErrorResponse.of(errorCode, errors);
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.valueOf(errorCode.getStatus()));
    }

}
