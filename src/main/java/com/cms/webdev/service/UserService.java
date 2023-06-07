package com.cms.webdev.service;

import com.cms.webdev.dto.response.UserDto;
import com.cms.webdev.entity.User;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    User findByUserId(Integer userId);
}
