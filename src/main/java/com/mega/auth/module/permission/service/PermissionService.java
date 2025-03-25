package com.mega.auth.module.permission.service;

import com.mega.auth.module.permission.dto.CreateNewPermissionDto;
import com.mega.auth.module.permission.dto.ListPermissionDto;
import com.mega.auth.module.permission.dto.SinglePermissionDto;
import com.mega.auth.module.permission.dto.UpdatePermissionDto;
import com.mega.auth.module.permission.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<ListPermissionDto> findAllActivePermissions();
    SinglePermissionDto findPermissionById(Long id);
    Permission createNewPermission(CreateNewPermissionDto payload);
    Permission updatePermission(Long id, UpdatePermissionDto payload);
    void deletePermission(Long id);
}
