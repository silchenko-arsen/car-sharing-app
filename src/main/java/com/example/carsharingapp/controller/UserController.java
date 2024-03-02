package com.example.carsharingapp.controller;

import com.example.carsharingapp.dto.user.UserUpdateRequestDto;
import com.example.carsharingapp.dto.user.UserUpdateResponseDto;
import com.example.carsharingapp.model.User;
import com.example.carsharingapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}/role")
    @PreAuthorize("hasAuthority('MANAGER')")
    public UserUpdateResponseDto updateUserRole(
            @PathVariable Long id,
            @RequestParam User.Role newRole
    ) {
        return userService.updateRole(id, newRole);
    }

    @GetMapping("/me")
    public UserUpdateResponseDto getMyProfileInfo() {
        return userService.getMyProfileInfo(getAuthenticationName());
    }

    @PutMapping("/me")
    public UserUpdateResponseDto updateMyProfileInfo(
                @Valid @RequestBody UserUpdateRequestDto user) {
        return userService.updateMyProfileInfo(user, getAuthenticationName());
    }

    private String getAuthenticationName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
