 package com.example.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminIndex(Model model) {
        // SecurityConfig already restricts /admin/** to ROLE_ADMIN
        return "admin/index";
    }

    @GetMapping("/admin/users")
    public String manageUsers() {
        return "admin/users";
    }

    @GetMapping("/admin/orders")
    public String manageOrders() {
        return "admin/orders";
    }
}
