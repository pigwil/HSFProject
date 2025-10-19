package com.example.laptopshop.util;

import com.example.laptopshop.dto.RegisterRequest;
import com.example.laptopshop.dto.UserResponse;
import com.example.laptopshop.entity.User;

import java.util.List;

public final class UserUtils {

    private UserUtils() {}

    // Convert RegisterRequest -> User entity (do NOT encode password here)
    public static User toEntity(RegisterRequest dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setPassword(dto.getPassword()); // encode in service before saving
        return user;
    }

    // Convert User entity -> UserResponse DTO
    public static UserResponse toDto(User user) {
        if (user == null) return null;
        UserResponse r = new UserResponse();
        r.setId(user.getId());
        r.setUsername(user.getUsername());
        r.setFullName(user.getFullName());

        // Nếu user có role
        if (user.getRole() != null) {
            r.setRoles(List.of(user.getRole().getRoleName()));
        }

        return r;
    }
}
