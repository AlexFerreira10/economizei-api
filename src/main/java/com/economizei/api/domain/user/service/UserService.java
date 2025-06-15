package com.economizei.api.domain.user.service;

import com.economizei.api.domain.user.model.User;
import com.economizei.api.domain.user.model.dto.*;
import com.economizei.api.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public DataUserDto registerUser(RegisterUserDto registerUserDto) {

        User user = new User(
                registerUserDto.name(),
                registerUserDto.phone(),
                registerUserDto.cpf(),
                registerUserDto.email(),
                registerUserDto.password(),
                new ArrayList<>()
        );

        userRepository.save(user);

        return new DataUserDto(user);
    }

    public DataUserDto getUserById(Long id) {
        existsById(id);

        return new DataUserDto(userRepository.getReferenceById(id));
    }

    @Transactional
    public DataUserDto updateDataUser(UpdateUserDto updateUserDto) {
        existsById(updateUserDto.id());

        User user = userRepository.getReferenceById(updateUserDto.id());

        user.updateDataUser(updateUserDto);

        return new DataUserDto(user);
    }

    @Transactional
    public void disableUserAccount (Long id) {
        existsById(id);

        User user = userRepository.getReferenceById(id);

        user.setIsActive(false);
    }

    public User getUserEntityById(Long id) {
        existsById(id);
        return userRepository.getReferenceById(id);
    }

    public List<User> getAllUsersEntity() {
        return userRepository.findAll();
    }

    @Transactional
    public DataUserDto changePassword(@Valid ChangePasswordDto dto) {
        existsById(dto.userId());
        User user = userRepository.getReferenceById(dto.userId());
        user.setPassword(dto.newPassword());
        return new DataUserDto(user);
    }

    @Transactional
    public DataUserDto changeEmail(@Valid ChangeEmailDto dto) {
        existsById(dto.userId());
        User user = userRepository.getReferenceById(dto.userId());
        user.setEmail(dto.newEmail());
        return new DataUserDto(user);
    }


    private void existsById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
    }
}
