package com.mega.auth.module.user.dto;

import com.mega.auth.module.permission.dto.ListPermissionDto;
import lombok.Data;

import java.util.List;

@Data
public class LoginUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String photo;
    private String token;
    private String address;
    private List<ListPermissionDto> permissions;
}
