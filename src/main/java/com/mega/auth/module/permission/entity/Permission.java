package com.mega.auth.module.permission.entity;

import com.mega.auth.module.role.entity.Role;
import com.mega.auth.utils.auditing.Auditing;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "permissions")
@Data
public class Permission extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "object")
    private String object;

    @Column(name = "action")
    private String action;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
