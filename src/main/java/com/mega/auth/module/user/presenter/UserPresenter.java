package com.mega.auth.module.user.presenter;

import com.mega.auth.module.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserPresenter {
    private final UserServiceImpl userService;

    @GetMapping("/users")
    public Map<String, Object> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", userService.findAllActiveUsers());
        return response;
    }

    @GetMapping("/users/{id}")
    public Map<String, Object> getUserById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", userService.findUserById(id));
        return response;
    }
}
