package com.pm.authservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "email should be a valid email address")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password must be 8 char long")
    private String password;
}
