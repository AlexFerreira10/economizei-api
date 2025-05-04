package com.economizei.api.user.domain.model;

import com.economizei.api.statment.domain.model.Statement;
import com.economizei.api.user.domain.model.dto.UpdateUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private String phone;
    private String email;
    private String password;
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Statement> statements = new ArrayList<>();

    public User(String name, String phone, String cpf, String email, String password, List<Statement> statements) {
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.statements = statements;
        this.isActive = true;
    }

    public void updateDataUser(UpdateUserDto updateUserDto) {
        if (updateUserDto.name() != null) {
            this.name = updateUserDto.name();
        }
        if (updateUserDto.phone() != null) {
            this.phone = updateUserDto.phone();
        }
        if (updateUserDto.email() != null) {
            this.email = updateUserDto.email();
        }
        if (updateUserDto.password() != null) {
            this.password = updateUserDto.password();
        }
    }
}