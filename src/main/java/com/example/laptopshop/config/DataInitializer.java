package com.example.laptopshop.config;

import com.example.laptopshop.entity.Laptop;
import com.example.laptopshop.entity.Role;
import com.example.laptopshop.entity.User;
import com.example.laptopshop.repository.RoleRepository;
import com.example.laptopshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

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

        // Create default admin if not present
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            // Sử dụng userCode ngắn hơn thay vì UUID.randomUUID().toString() (36 ký tự)
            admin.setUserCode("ADMIN001"); // Chỉ 8 ký tự
            admin.setFullName("Administrator");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // BCrypt ~ 60 chars
            admin.setAddress("N/A"); // Thay vì chuỗi rỗng
            admin.setPhone("N/A"); // Thay vì chuỗi rỗng
            admin.setRole(adminRole);

            try {
                userRepository.save(admin);
                System.out.println("✓ Admin user created successfully!");
            } catch (Exception e) {
                System.err.println("✗ Failed to create admin user: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("✓ Admin user already exists!");
        }
        if (userRepository.findByUsername("user") == null) {
            User user = new User();
            user.setUserCode("USER001");
            user.setFullName("Nguyễn Văn A");
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setAddress("TP. Hồ Chí Minh");
            user.setPhone("0987654321");
            user.setRole(userRole);
            userRepository.save(user);
            System.out.println("✓ Created User: user / user123");
        }

    }
}