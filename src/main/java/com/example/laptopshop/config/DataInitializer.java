
package com.example.laptopshop.config;

import com.example.laptopshop.entity.Role;
import com.example.laptopshop.entity.User;
import com.example.laptopshop.repository.RoleRepository;
import com.example.laptopshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setRoleName("ROLE_USER");
            roleRepository.save(userRole);
        }

        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setRoleName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        // create default admin if not present
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUserCode(UUID.randomUUID().toString());
            admin.setFullName("Administrator");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // change password after first login
            admin.setAddress("");
            admin.setPhone("");
            admin.setRole(adminRole);
            userRepository.save(admin);
        }
    }
}
