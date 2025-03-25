package com.mega.auth.module.permission.dto;

import com.mega.auth.module.role.dto.SingleRoleDto;
import lombok.Data;

@Data
public class SinglePermissionDto {
    private Long id;
    private String name;
    private String object;
    private String action;
    private SingleRoleDto role;
}
