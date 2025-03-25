package com.mega.auth.module.role.service;

import com.mega.auth.module.role.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllActiveRoles();
    Role findRoleById(Long id);
    Role createNewRole(Role role);
    Role updateRole(Long id, Role role);
    void deleteRole(Long id);
}
