package com.cms.webdev.service;

import com.cms.webdev.common.handler.exceptions.AppException;
import com.cms.webdev.common.utils.ErrorMessage;
import com.cms.webdev.dto.response.UserDto;
import com.cms.webdev.entity.User;
import com.cms.webdev.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDto registerUser(UserDto userDto) {
        logger.info("registering a new user");
        if(this.existsByUsername(userDto.getUsername())) {
            throw new AppException(ErrorMessage.USERNAME_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        if(this.existsByEmail(userDto.getEmail())) {
            throw new AppException(ErrorMessage.EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User findByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    private Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    private Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
