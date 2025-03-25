package com.mega.auth.module.user.service;

import com.mega.auth.module.user.entity.User;
import com.mega.auth.module.user.repository.UserRepository;
import com.mega.auth.utils.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAllActiveUsers() {
        return userRepository.findAllActiveUsers();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found :" + id));
    }
}
