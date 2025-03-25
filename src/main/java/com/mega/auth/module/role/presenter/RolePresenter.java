package com.mega.auth.module.role.presenter;

import com.mega.auth.module.role.entity.Role;
import com.mega.auth.module.role.service.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RolePresenter {
    private final RoleServiceImpl roleService;

    @GetMapping("/roles")
    public Map<String, Object> getAllRoles() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", roleService.findAllActiveRoles());
        return response;
    }

    @GetMapping("/roles/{id}")
    public Map<String, Object> getRoleById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", roleService.findRoleById(id));
        return response;
    }

    @PostMapping("/roles")
    public Map<String, Object> createRole(@RequestBody Role payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", roleService.createNewRole(payload));
        return response;
    }

    @PutMapping("/roles/{id}")
    public Map<String, Object> updateRole(@PathVariable Long id, @RequestBody Role payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", roleService.updateRole(id, payload));
        return response;
    }

    @DeleteMapping("/roles/{id}")
    public Map<String, Object> deleteRole(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        roleService.deleteRole(id);
        return response;
    }
}
