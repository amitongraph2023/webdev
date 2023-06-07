package com.cms.webdev.controller;

import com.cms.webdev.common.annotations.BaseURL;
import com.cms.webdev.common.handler.response.ResponseDTO;
import com.cms.webdev.common.handler.response.ResponseUtil;
import com.cms.webdev.dto.response.UserDto;
import com.cms.webdev.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@BaseURL
@RestController
public class UserController {
    private final  ResponseUtil responseUtil;
    private final UserService userService;
    UserController(ResponseUtil responseUtil, UserService userService) {
        this.responseUtil = responseUtil;
        this.userService = userService;
    }
    @GetMapping("users")
    public ResponseDTO<String> getAllUsers() {
        return responseUtil.ok();
    }

    @PostMapping("users/register")
    public ResponseDTO<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        return responseUtil.ok(userService.registerUser(userDto));
    }

}
