package com.mega.auth.module.permission.service;

import com.mega.auth.configuration.ModelMapperConfig;
import com.mega.auth.module.permission.dto.CreateNewPermissionDto;
import com.mega.auth.module.permission.dto.ListPermissionDto;
import com.mega.auth.module.permission.dto.SinglePermissionDto;
import com.mega.auth.module.permission.dto.UpdatePermissionDto;
import com.mega.auth.module.permission.entity.Permission;
import com.mega.auth.module.permission.repository.PermissionRepository;
import com.mega.auth.module.role.dto.SingleRoleDto;
import com.mega.auth.module.role.entity.Role;
import com.mega.auth.module.role.repository.RoleRepository;
import com.mega.auth.utils.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final ModelMapperConfig modelMapper;

    @Override
    public List<ListPermissionDto> findAllActivePermissions() {
        List<Permission> permissions = permissionRepository.findAllActivePermissions();
        return mapperListPermissionToDto(permissions);
    }

    @Override
    public SinglePermissionDto findPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Permission not found :" + id));
        return mapperPermissionToDto(permission);
    }

    @Override
    public Permission createNewPermission(CreateNewPermissionDto payload) {
        Role role = roleRepository.findById(payload.getRoleId())
                .orElseThrow(() -> new ResourceNotFound("Role not found :" + payload.getRoleId()));
        Permission permission = new Permission();
        permission.setName(payload.getName());
        permission.setObject(payload.getObject());
        permission.setAction(payload.getAction());
        permission.setRole(role);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, UpdatePermissionDto payload) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Permission not found :" + id));
        Role role = roleRepository.findById(payload.getRoleId())
                .orElseThrow(() -> new ResourceNotFound("Role not found :" + payload.getRoleId()));
        permission.setName(payload.getName());
        permission.setObject(payload.getObject());
        permission.setAction(payload.getAction());
        permission.setRole(role);
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Permission not found :" + id));
        permission.setDeletedAt(LocalDateTime.now());
        permissionRepository.save(permission);
    }

    private List<ListPermissionDto> mapperListPermissionToDto(List<Permission> permissions) {
        List<ListPermissionDto> listPermissionDtos = new ArrayList<>();

        for (Permission permission : permissions) {
            ListPermissionDto listPermissionDto = modelMapper
                    .modelMapper()
                    .map(permission, ListPermissionDto.class);
            listPermissionDtos.add(listPermissionDto);
        }

        return listPermissionDtos;
    }

    private SinglePermissionDto mapperPermissionToDto(Permission permission) {
        Role role = roleRepository.findById(permission.getRole().getId())
                .orElseThrow(() -> new ResourceNotFound("Role not found :" + permission.getRole().getId()));
        SinglePermissionDto singlePermissionDto = modelMapper
                .modelMapper()
                .map(permission, SinglePermissionDto.class);
        SingleRoleDto roleDto = modelMapper.modelMapper().map(role, SingleRoleDto.class);
        singlePermissionDto.setRole(roleDto);

        return singlePermissionDto;
    }
}