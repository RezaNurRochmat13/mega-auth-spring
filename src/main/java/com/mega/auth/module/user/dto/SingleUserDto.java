package com.mega.auth.module.user.dto;

import com.mega.auth.module.role.dto.SingleRoleDto;
import com.mega.auth.utils.auditing.Auditing;
import lombok.Data;

@Data
public class SingleUserDto extends Auditing {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String photo;
    private SingleRoleDto role;
}
