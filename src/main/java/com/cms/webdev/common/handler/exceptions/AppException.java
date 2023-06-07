package com.cms.webdev.common.handler.exceptions;
import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 7162330549370241516L;
    private HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
