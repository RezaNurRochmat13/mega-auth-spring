package com.mega.auth.module.auth.dto;

import lombok.Data;

@Data
public class LoginUserRequestDto {
    private String username;
    private String password;
}
