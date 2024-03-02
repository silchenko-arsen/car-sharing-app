package com.example.carsharingapp.service.impl;

import com.example.carsharingapp.dto.user.UserRegistrationRequestDto;
import com.example.carsharingapp.dto.user.UserRegistrationResponseDto;
import com.example.carsharingapp.dto.user.UserUpdateRequestDto;
import com.example.carsharingapp.dto.user.UserUpdateResponseDto;
import com.example.carsharingapp.exception.RegistrationException;
import com.example.carsharingapp.exception.UserNotFoundException;
import com.example.carsharingapp.mapper.UserMapper;
import com.example.carsharingapp.model.User;
import com.example.carsharingapp.repository.UserRepository;
import com.example.carsharingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegistrationResponseDto register(
            UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User already exists");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setEmail(requestDto.getEmail());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setRole(User.Role.CUSTOMER);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserUpdateResponseDto updateRole(Long id, User.Role newRole) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setRole(newRole);
        return userMapper.toUserUpdateResponse(userRepository.save(user));
    }

    @Override
    public UserUpdateResponseDto getMyProfileInfo(String email) {
        User currentUser = userRepository
                .findByEmail(email)
                .orElseThrow(()
                        -> new UserNotFoundException(email));
        return userMapper.toUserUpdateResponse(currentUser);
    }

    @Override
    public UserUpdateResponseDto updateMyProfileInfo(UserUpdateRequestDto updatedUser,
                                                     String email) {
        User currentUser = userRepository
                .findByEmail(email)
                .orElseThrow(()
                        -> new UserNotFoundException(email));
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        return userMapper.toUserUpdateResponse(userRepository.save(currentUser));
    }
}
