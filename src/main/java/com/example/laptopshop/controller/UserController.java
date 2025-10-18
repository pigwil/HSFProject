package com.example.laptopshop.controller;

import com.example.laptopshop.dto.UserResponse;
import com.example.laptopshop.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/cart")
    public String cartPage(@AuthenticationPrincipal UserDetails principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        UserResponse user = userService.findByUsername(principal.getUsername());
        model.addAttribute("user", user);
        return "cart";
    }

    @GetMapping("/orders")
    public String ordersPage(@AuthenticationPrincipal UserDetails principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        String username = principal.getUsername();
        UserResponse user = userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("orders", userService.getOrdersForUser(username));
        return "orders";
    }

    @GetMapping("/checkout")
    public String checkoutPage(@AuthenticationPrincipal UserDetails principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", userService.findByUsername(principal.getUsername()));
        return "checkout";
    }
}
