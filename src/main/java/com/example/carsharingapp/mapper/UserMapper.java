package com.example.carsharingapp.mapper;

import com.example.carsharingapp.dto.user.UserRegistrationResponseDto;
import com.example.carsharingapp.dto.user.UserUpdateResponseDto;
import com.example.carsharingapp.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRegistrationResponseDto toUserResponse(User user);

    UserUpdateResponseDto toUserUpdateResponse(User user);
}
