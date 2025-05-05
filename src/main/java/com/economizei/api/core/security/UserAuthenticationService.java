package com.economizei.api.core.security;

import com.economizei.api.domain.user.model.User;
import com.economizei.api.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserAuthenticationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) repository.findByEmail(username);

        if (!user.getIsActive()) {
            throw new DisabledException("User is disabled, please contact support");
        }

        return user;
    }
}