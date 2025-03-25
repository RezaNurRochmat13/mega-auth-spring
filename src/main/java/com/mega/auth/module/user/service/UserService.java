package com.mega.auth.module.user.service;

import com.mega.auth.module.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllActiveUsers();
    User findUserById(Long id);
}
