package com.mega.auth.module.auth.dto;

import lombok.Data;

@Data
public class UpdateUserProfile {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String photo;
}
