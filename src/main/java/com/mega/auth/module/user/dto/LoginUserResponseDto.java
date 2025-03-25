package com.mega.auth.module.user.dto;

import lombok.Data;

@Data
public class LoginUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String photo;
    private String token;
    private String address;
}
