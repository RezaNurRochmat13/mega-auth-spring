package com.mega.auth.module.auth.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ForgotPasswordDto {
    private String username;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).*$",
            message = "Password must contain at least one special character"
    )
    private String password;
}
