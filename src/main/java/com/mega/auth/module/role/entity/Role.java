package com.mega.auth.module.role.entity;

import com.mega.auth.module.permission.entity.Permission;
import com.mega.auth.utils.auditing.Auditing;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    List<Permission> permissions;
}
