package com.economizei.api.controllers;

import com.economizei.api.core.security.Dto.DataAuthenticationDto;
import com.economizei.api.core.security.Dto.DataTokenJwtDto;
import com.economizei.api.core.security.TokenService;
import com.economizei.api.domain.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "User authentication")
@RestController
@RequestMapping("/authentication")
public class UserAuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "User login with email and password")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DataAuthenticationDto data) {

        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJwtDto(tokenJWT));
    }
}
