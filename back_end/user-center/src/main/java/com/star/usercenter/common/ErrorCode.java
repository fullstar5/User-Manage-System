package com.star.usercenter.common;

public enum ErrorCode {

    PARAMS_ERROR(40000, "request params error", ""),
    NULL_ERROR(40001, "request params empty", ""),
    NO_AUTH(40101, "not authorised", ""),
    NOT_LOGIN(40100, "not login", ""),
    SUCCESS(0, "ok", ""),
    SYSTEM_ERROR(500, "internal system error", ""),
    ;

    private final int code;

    private final String message;

    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
