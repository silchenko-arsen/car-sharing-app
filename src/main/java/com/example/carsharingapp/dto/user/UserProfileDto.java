package com.example.carsharingapp.dto.user;

import com.example.carsharingapp.model.User;

public record UserProfileDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        User.Role role
) {

}
