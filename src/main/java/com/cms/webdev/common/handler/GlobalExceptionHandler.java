package com.cms.webdev.common.handler;

import com.cms.webdev.common.handler.exceptions.AppException;
import com.cms.webdev.common.handler.response.ApiResponseDTO;
import com.cms.webdev.common.handler.response.ResponseDTO;
import com.cms.webdev.common.handler.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    ResponseUtil responseUtil;
    @ExceptionHandler(Exception.class)
    public ResponseDTO<ApiResponseDTO> handleException(Exception e){
        return responseUtil.exception(500,e.getMessage());
    }
    
    @ExceptionHandler(AppException.class)
    public ResponseDTO<ApiResponseDTO> handleAppxception(AppException e){
        return responseUtil.exception(e.getStatus().value(),e.getMessage());
    }
}
