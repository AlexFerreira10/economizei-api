package com.economizei.api.controlleres;

import com.economizei.api.domain.user.model.dto.DataUserDto;
import com.economizei.api.domain.user.model.dto.RegisterUserDto;
import com.economizei.api.domain.user.model.dto.UpdateUserDto;
import com.economizei.api.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Tag(name = "Users", description = "APIs for managing users")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<DataUserDto> register(@RequestBody @Valid RegisterUserDto dto, UriComponentsBuilder uriBuilder) {
        DataUserDto created = userService.registerUser(dto);

        URI uri = uriBuilder
                .path("/users/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DataUserDto> getById(@PathVariable Long id) {
        DataUserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }


    @Operation(summary = "Update user data")
    @PutMapping("/update")
    public ResponseEntity<DataUserDto> update(@RequestBody @Valid UpdateUserDto dto) {
        DataUserDto updated = userService.updateDataUser(dto);

        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Disable a user account")
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Long id) {
        userService.disableUserAccount(id);

        return ResponseEntity.noContent().build();
    }
}