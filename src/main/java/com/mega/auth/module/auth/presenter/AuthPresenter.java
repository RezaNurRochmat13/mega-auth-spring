package com.mega.auth.module.auth.presenter;

import com.mega.auth.module.auth.service.AuthServiceImpl;
import com.mega.auth.module.user.dto.RegisterUserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
