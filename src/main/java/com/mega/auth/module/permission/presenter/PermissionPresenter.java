package com.mega.auth.module.permission.presenter;

import com.mega.auth.module.permission.dto.CreateNewPermissionDto;
import com.mega.auth.module.permission.dto.UpdatePermissionDto;
import com.mega.auth.module.permission.entity.Permission;
import com.mega.auth.module.permission.service.PermissionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PermissionPresenter {
    private final PermissionServiceImpl permissionService;

    @GetMapping("/permissions")
    public Map<String, Object> getAllPermissions() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", permissionService.findAllActivePermissions());
        return response;
    }

    @GetMapping("/permissions/{id}")
    public Map<String, Object> getPermissionById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", permissionService.findPermissionById(id));
        return response;
    }

    @PostMapping("/permissions")
    public Map<String, Object> createPermission(@RequestBody CreateNewPermissionDto payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", permissionService.createNewPermission(payload));
        return response;
    }

    @PutMapping("/permissions/{id}")
    public Map<String, Object> updatePermission(@PathVariable Long id, @RequestBody UpdatePermissionDto payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", permissionService.updatePermission(id, payload));
        return response;
    }

    @DeleteMapping("/permissions/{id}")
    public Map<String, Object> deletePermission(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        permissionService.deletePermission(id);
        return response;
    }
}
