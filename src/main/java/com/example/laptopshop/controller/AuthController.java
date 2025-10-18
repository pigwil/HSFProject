
        package com.example.laptopshop.controller;

import com.example.laptopshop.dto.RegisterRequest;
import com.example.laptopshop.dto.UserResponse;
import com.example.laptopshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // return login view
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        if (!model.containsAttribute("registerRequest")) {
            model.addAttribute("registerRequest", new RegisterRequest());
        }
        return "register"; // return register view
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("registerRequest") RegisterRequest registerRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerRequest", registerRequest);
            return "register";
        }

        try {
            UserResponse created = userService.register(registerRequest);
            redirectAttributes.addFlashAttribute("message", "Registration successful. Please log in.");
            return "redirect:/login?registered";
        } catch (IllegalArgumentException ex) {
            bindingResult.rejectValue("username", "error.registerRequest", ex.getMessage());
            return "register";
        } catch (Exception ex) {
            model.addAttribute("error", "Unexpected error: " + ex.getMessage());
            return "register";
        }
    }
}
