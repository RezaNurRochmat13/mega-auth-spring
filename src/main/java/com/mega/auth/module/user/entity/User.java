package com.mega.auth.module.user.entity;

import com.mega.auth.module.role.entity.Role;
import com.mega.auth.utils.auditing.Auditing;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).*$",
            message = "Password must contain at least one special character"
    )
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
