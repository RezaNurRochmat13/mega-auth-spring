package com.mega.auth.module.role.dto;

import com.mega.auth.utils.auditing.Auditing;
import lombok.Data;

@Data
public class SingleRoleDto extends Auditing {
    private Long id;
    private String name;
}
