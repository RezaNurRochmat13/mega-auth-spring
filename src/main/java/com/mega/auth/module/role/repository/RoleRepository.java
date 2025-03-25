package com.mega.auth.module.role.repository;

import com.mega.auth.module.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.deletedAt IS NULL")
    List<Role> findAllActiveRoles();
}
