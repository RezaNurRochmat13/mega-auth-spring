package com.mega.auth.module.auth.service;

import com.mega.auth.module.user.dto.CustomUserDetails;
import com.mega.auth.module.user.entity.User;
import com.mega.auth.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Searching for user: " + username);  // Log the username being searched
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOpt.get();
        System.out.println("User found: " + user.getUsername());
        return new CustomUserDetails(user.getUsername(), user.getPassword(), user.getRole().getName());
    }
}
