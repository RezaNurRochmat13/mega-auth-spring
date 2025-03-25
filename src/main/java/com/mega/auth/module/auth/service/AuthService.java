package com.mega.auth.module.auth.service;

import com.mega.auth.module.user.dto.LoginUserResponseDto;
import com.mega.auth.module.user.dto.RegisterUserDto;
import com.mega.auth.module.user.entity.User;

public interface AuthService {
    User registerUser(RegisterUserDto registerUserDto);
    LoginUserResponseDto loginUser(String username, String password);
}
