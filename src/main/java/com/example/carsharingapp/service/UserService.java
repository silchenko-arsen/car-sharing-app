package com.example.carsharingapp.service;

import com.example.carsharingapp.dto.user.UserRegistrationRequestDto;
import com.example.carsharingapp.dto.user.UserRegistrationResponseDto;
import com.example.carsharingapp.dto.user.UserUpdateRequestDto;
import com.example.carsharingapp.dto.user.UserUpdateResponseDto;
import com.example.carsharingapp.exception.RegistrationException;
import com.example.carsharingapp.model.User;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException;

    UserUpdateResponseDto updateRole(Long id, User.Role newRole);

    UserUpdateResponseDto getMyProfileInfo(String authenticationName);

    UserUpdateResponseDto updateMyProfileInfo(UserUpdateRequestDto user, String email);
}
