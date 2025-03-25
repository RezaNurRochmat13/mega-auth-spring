package com.mega.auth.module.permission.dto;

import com.mega.auth.module.role.dto.SingleRoleDto;
import com.mega.auth.utils.auditing.Auditing;
import lombok.Data;

@Data
public class ListPermissionDto extends Auditing {
    private Long id;
    private String name;
    private String object;
    private String action;
    private SingleRoleDto role;
}
