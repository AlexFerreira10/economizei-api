package com.economizei.api.domain.user.model.dto;

import com.economizei.api.domain.user.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserDto(
        @NotBlank(message = "The name field must not be blank")
        String name,

        @NotBlank(message = "The CPF field must not be blank")
        String cpf,

        @NotBlank(message = "The email field must not be blank")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "The phone field must not be blank")
        String phone,

        @NotBlank(message = "The password field must not be blank")
        String password
) {
    public RegisterUserDto(User user) {
        this(
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword()
        );
    }
}