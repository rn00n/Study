package me.rn00n.infra.error;

public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    ENTITY_NOT_FOUND(204, "C002", "Entity Not Found"),
    METHOD_NOT_ALLOWED(405, "C002", "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    BAD_REQUEST_EXCEPTION(400, "C007", "Bad Request Exception"),
    NO_CONTENT_EXCEPTION(204, "C008", "No Content Exception"),
    REST_TEMPLATE_EXCEPTION(500, "C009", "Rest Template Exception"),
    ALREADY_EXIST_EXCEPTION(400, "C010", "Already Exist Exception"),
    DO_NOT_EXIST_EXCEPTION(400, "C011", "Do Not Exist Exception"),

    // Account
    SIGN_UP_BAD_REQUEST(400, "A001", "Sign Up Bad Request"),
    ACCOUNT_NOT_FOUND(204, "A002", "Account Not Found"),
    ACCOUNT_INVALID_DELETED(401, "A003", "Deleted account"),
    ILLEGAL_ACCOUNT_STATUS_EXCEPTION(400, "A001", "Illegal Account Status Exception"),

    ;

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
