package com.example.project1.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
}
