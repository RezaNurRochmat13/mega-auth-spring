package com.mega.auth.module.auth.service;

import com.mega.auth.configuration.ModelMapperConfig;
import com.mega.auth.module.role.entity.Role;
import com.mega.auth.module.role.repository.RoleRepository;
import com.mega.auth.module.user.dto.LoginUserResponseDto;
import com.mega.auth.module.user.dto.RegisterUserDto;
import com.mega.auth.module.user.entity.User;
import com.mega.auth.module.user.repository.UserRepository;
import com.mega.auth.utils.exception.ResourceNotFound;
import com.mega.auth.utils.exception.UserAlreadyExist;
import com.mega.auth.utils.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final ModelMapperConfig modelMapper;
    private final JwtUtil jwtUtil;

    @Override
    public User registerUser(RegisterUserDto registerUserDto) {
        if (userRepository.findByUsername(registerUserDto.getUsername()).isPresent()) {
            throw new UserAlreadyExist("User already exist");
        }

        Role role = roleRepository.findById(registerUserDto.getRoleId())
                .orElseThrow(() -> new ResourceNotFound("Role not found :" + registerUserDto.getRoleId()));

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setEmail(registerUserDto.getEmail());
        user.setPhone(registerUserDto.getPhone());
        user.setAddress(registerUserDto.getAddress());
        user.setPhoto(registerUserDto.getPhoto());
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public LoginUserResponseDto loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User not found :" + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        LoginUserResponseDto loginUserResponseDto = modelMapper
                .modelMapper()
                .map(user, LoginUserResponseDto.class);
        loginUserResponseDto.setToken(jwtUtil.generateToken(user.getUsername(), user.getRole().getName()));
        return loginUserResponseDto;
    }
}
