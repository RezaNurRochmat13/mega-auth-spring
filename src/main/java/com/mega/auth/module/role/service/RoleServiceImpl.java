package com.mega.auth.module.role.service;

import com.mega.auth.module.role.entity.Role;
import com.mega.auth.module.role.repository.RoleRepository;
import com.mega.auth.utils.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAllActiveRoles() {
        return roleRepository.findAllActiveRoles();
    }

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Role not found :" + id));
    }

    @Override
    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, Role payload) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Role not found :" + id));
        role.setName(payload.getName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
      Role role = roleRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFound("Role not found :" + id));
      role.setDeletedAt(LocalDateTime.now());
      roleRepository.save(role);
    }
}
