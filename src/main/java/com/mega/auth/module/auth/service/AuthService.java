package com.mega.auth.module.auth.service;

import com.mega.auth.module.auth.dto.ForgotPasswordDto;
import com.mega.auth.module.auth.dto.LoginUserResponseDto;
import com.mega.auth.module.auth.dto.RegisterUserDto;
import com.mega.auth.module.auth.dto.UpdateUserProfile;
import com.mega.auth.module.user.entity.User;

public interface AuthService {
    User registerUser(RegisterUserDto registerUserDto);
    LoginUserResponseDto loginUser(String username, String password);
    User forgotPassword(ForgotPasswordDto payload);
    User updateUserProfile(Long id, UpdateUserProfile payload);
}
