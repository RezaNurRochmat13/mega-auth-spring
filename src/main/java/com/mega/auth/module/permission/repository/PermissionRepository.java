package com.mega.auth.module.permission.repository;

import com.mega.auth.module.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query("SELECT p FROM Permission p WHERE p.deletedAt IS NULL")
    List<Permission> findAllActivePermissions();
}
