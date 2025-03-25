package com.mega.auth.module.auth.service;

import com.mega.auth.configuration.ModelMapperConfig;
import com.mega.auth.module.auth.dto.ForgotPasswordDto;
import com.mega.auth.module.permission.dto.ListPermissionDto;
import com.mega.auth.module.permission.entity.Permission;
import com.mega.auth.module.permission.repository.PermissionRepository;
import com.mega.auth.module.role.entity.Role;
import com.mega.auth.module.role.repository.RoleRepository;
import com.mega.auth.module.auth.dto.LoginUserResponseDto;
import com.mega.auth.module.auth.dto.RegisterUserDto;
import com.mega.auth.module.user.entity.User;
import com.mega.auth.module.user.repository.UserRepository;
import com.mega.auth.utils.exception.ResourceNotFound;
import com.mega.auth.utils.exception.UserAlreadyExist;
import com.mega.auth.utils.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
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
        List<ListPermissionDto> listPermissionDtos = new ArrayList<>();
        List<Permission> permissionByRole = permissionRepository.findByRoleId(user.getRole().getId());

        for (Permission permission : permissionByRole) {
            ListPermissionDto listPermissionDto = modelMapper
                    .modelMapper()
                    .map(permission, ListPermissionDto.class);
            listPermissionDtos.add(listPermissionDto);
        }

        loginUserResponseDto.setPermissions(listPermissionDtos);
        loginUserResponseDto.setToken(jwtUtil.generateToken(user.getUsername(), user.getRole().getName()));
        return loginUserResponseDto;
    }

    @Override
    public User forgotPassword(ForgotPasswordDto payload) {
        User user = userRepository.findByUsername(payload.getUsername())
                .orElseThrow(() -> new ResourceNotFound("User not found :" + payload.getUsername()));
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        return userRepository.save(user);
    }
}
