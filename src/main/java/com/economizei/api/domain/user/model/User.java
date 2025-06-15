package com.economizei.api.domain.user.model;

import com.economizei.api.domain.statement.model.Statement;
import com.economizei.api.domain.user.model.dto.UpdateUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;
    private String password;
    private Boolean isActive;

    public User(String name, String phone, String cpf, String email, String password, List<Statement> statements) {
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.email = email;
        this.password = encryptPassword(password);
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

    protected String encryptPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public void setPassword(String password) {
        this.password = encryptPassword(password);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}