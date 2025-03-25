package com.mega.auth.module.user.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private Long roleId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String photo;
}
