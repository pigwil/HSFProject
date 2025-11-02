
        package com.example.laptopshop.service;

import com.example.laptopshop.dto.RegisterRequest;
import com.example.laptopshop.dto.UserResponse;
import com.example.laptopshop.entity.Role;
import com.example.laptopshop.entity.User;
import com.example.laptopshop.util.UserUtils;
import com.example.laptopshop.repository.RoleRepository;
import com.example.laptopshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.*;

        @Service
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse register(RegisterRequest req) {
        // check duplicates
        if (userRepo.findByUsername(req.getUsername()) != null) {
            throw new IllegalArgumentException("username exists");
        }

        User user = UserUtils.toEntity(req);
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        // assign default role
        Role userRole = roleRepo.findByRoleName("ROLE_USER");
        user.setRole(userRole); // or user.getRoles().add(userRole) depending on entity
        User saved = userRepo.save(user);

        return UserUtils.toDto(saved);
    }

    /**
     * Return UserResponse for the given username (or null if not found).
     */
    public UserResponse findByUsername(String username) {
        if (username == null) return null;
        User user = userRepo.findByUsername(username);
        return UserUtils.toDto(user);
    }

    /**
     * Return a list of orders for the given username.
     * Attempts to call `getOrders()` on the User entity via reflection.
     * If no such method or no orders exist, returns an empty list.
     */
    public List<Object> getOrdersForUser(String username) {
        if (username == null) return Collections.emptyList();
        User user = userRepo.findByUsername(username);
        if (user == null) return Collections.emptyList();

        try {
            Method m = user.getClass().getMethod("getOrders");
            Object orders = m.invoke(user);
            if (orders instanceof Collection) {
                return new ArrayList<>((Collection<?>) orders);
            }
        } catch (Exception ignored) { }

        return Collections.emptyList();
    }
    // login handled by Spring Security; you may expose an endpoint that forwards to form-login
}
