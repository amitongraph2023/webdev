package com.cms.webdev.common.handler.response;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public final class ResponseUtil<T> {

    private static final Map data = new HashMap();

    public ResponseDTO<T> ok() {
        String message = ApiResponseCode.SUCCESS.getMessage();
        return new ApiResponseDTO(message, data);
    }

    public ResponseDTO<T> ok(Object data) {
        String message = ApiResponseCode.SUCCESS.getMessage();
        return new ApiResponseDTO(message, data);
    }

    public ResponseDTO<T> exception(int code, String message) {
        return new ApiResponseDTO(code, message, data);
    }

    public ResponseDTO<T> exception(ResponseCode responseCode) {
        String message = responseCode.getMessage();
        return new ApiResponseDTO(responseCode.getCode(), message, data);
    }
    public ResponseDTO<T> validationFailed(int code, String message) {
        return new ApiResponseDTO(code, message, data);
    }
}
