package com.mega.auth.module.auth.presenter;

import com.mega.auth.module.auth.dto.ForgotPasswordDto;
import com.mega.auth.module.auth.service.AuthServiceImpl;
import com.mega.auth.module.auth.dto.RegisterUserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthPresenter {
    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public Map<String, Object> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", authService.registerUser(registerUserDto));
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> loginUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", authService.loginUser(registerUserDto.getUsername(), registerUserDto.getPassword()));
        return response;
    }

    @PutMapping("/forgot-password")
    public Map<String, Object> forgotPassword(@Valid @RequestBody ForgotPasswordDto payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", authService.forgotPassword(payload));
        return response;
    }
}
