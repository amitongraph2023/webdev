package com.cms.webdev.common.handler.response;

public enum ApiResponseCode implements ResponseCode {
    SUCCESS(200, "Request Completed Successfully"),
    ERROR(500, "Sorry, something went wrong. We're working on it and get it fixed as soon as we can");


    private int code;
    private String message;

    ApiResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
