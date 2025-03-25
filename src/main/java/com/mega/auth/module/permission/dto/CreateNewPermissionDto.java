package com.mega.auth.module.permission.dto;

import lombok.Data;

@Data
public class CreateNewPermissionDto {
    private Long roleId;
    private String name;
    private String object;
    private String action;
}
