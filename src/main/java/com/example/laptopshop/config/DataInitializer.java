package com.example.laptopshop.config;

import com.example.laptopshop.entity.*;
import com.example.laptopshop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LaptopRepository laptopRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;


    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           LaptopRepository laptopRepository,
                           OrderRepository orderRepository,
                           OrderDetailRepository orderDetailRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.laptopRepository = laptopRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
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
        //Insert LAPTOP
        User user = userRepository.findByUsername("user");

            Laptop laptop = new Laptop();
            laptop.setLaptopCode("DELLXPS13");
            laptop.setLaptopName("Dell XPS 13");
            laptop.setLaptopStatus("Available");
            laptop.setBrand("Dell");
            laptop.setPrice(1200.00);
            laptop.setCpuInfo("Intel Core i7 12th Gen");
            laptop.setRamInfo("16GB LPDDR5");
            laptop.setUser(user);
            laptopRepository.save(laptop);

            Laptop laptop1 = new Laptop();
            laptop1.setLaptopCode("MBP14M3");
            laptop1.setLaptopName("MacBook Pro 14 M3");
            laptop1.setLaptopStatus("Available");
            laptop1.setBrand("Apple");
            laptop1.setPrice(1599.50);
            laptop1.setCpuInfo("Apple M3 Pro");
            laptop1.setRamInfo("18GB Unified Memory");
            laptop1.setUser(user);
            laptopRepository.save(laptop1);

            Laptop laptop2 = new Laptop();
            laptop2.setLaptopCode("LENOVOX1");
            laptop2.setLaptopName("Lenovo ThinkPad X1 Carbon");
            laptop2.setLaptopStatus("Available");
            laptop2.setBrand("Lenovo");
            laptop2.setPrice(950.00);
            laptop2.setCpuInfo("Intel Core i5 12th Gen");
            laptop2.setRamInfo("16GB LPDDR5");
            laptop2.setUser(user);
            laptopRepository.save(laptop2);

        //INSERT ORDERS

        Order order = new Order();
        order.setCreatedDate(LocalDateTime.now().toLocalDate());
        order.setOrderStatus("PENDING");
        order.setOrderType("ONLINE");
        order.setCreatedBy(user);
        order.setAttributes("123 ABC, TP.HCM; Note: Giao nhanh");
        orderRepository.save(order);

        //INSERT ORDER_DETAILS

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(1200.00);
        orderDetail.setUnitPrice(1200.00);
        orderDetail.setLaptop(laptop);
        orderDetailRepository.save(orderDetail);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setOrder(order);
        orderDetail1.setQuantity(2);
        orderDetail1.setTotalPrice(1900.00);
        orderDetail1.setUnitPrice(950.00);
        orderDetail1.setLaptop(laptop2);
        orderDetailRepository.save(orderDetail1);
    }
}