package com.example.laptopshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_code", length = 100, unique = true, nullable = false)
    private String userCode;
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    @Column(name = "address", length = 200)
    private String address;
    @Column(name = "phone", length = 15)
    private String phone;
    @Column(name = "username", unique = true, length = 100, nullable = false)
    private String username;
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Laptop> laptops;
}
