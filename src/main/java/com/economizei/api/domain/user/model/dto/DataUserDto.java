package com.economizei.api.domain.user.model.dto;

import com.economizei.api.domain.user.model.User;

public record DataUserDto(
        Long id,
        String name,
        String cpf,
        String phone,
        String email,
        String password
) {
    public DataUserDto(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword()
        );
    }
}