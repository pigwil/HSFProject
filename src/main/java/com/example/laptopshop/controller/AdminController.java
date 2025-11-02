 package com.example.laptopshop.controller;

import com.example.laptopshop.entity.Order;
import com.example.laptopshop.service.DashboardService;
import com.example.laptopshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

 @Controller
public class AdminController {
    @Autowired
    private OrderService orderService;

     @Autowired
     private DashboardService dashboardService;

     @GetMapping("/admin")
     public String adminIndex(Model model) {
         model.addAttribute("totalUsers", dashboardService.getTotalUsers());
         model.addAttribute("totalOrders", dashboardService.getTotalOrders());
         model.addAttribute("totalStock", dashboardService.getTotalStock());
         model.addAttribute("totalRevenue", dashboardService.getTotalRevenue());
         model.addAttribute("revenueToday", dashboardService.getRevenueToday());
         model.addAttribute("topProducts", dashboardService.getTop5Products());
         return "admin/dashboard";
     }

    @GetMapping("/admin/users")
    public String manageUsers() {
        return "admin/users";
    }

    // Xem tất cả đơn hàng
    @GetMapping("/admin/orders")
    public String viewAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/orders";
    }

    // Xem chi tiết đơn hàng
    @GetMapping("/admin/orders/{id}")
    public String viewOrderDetail(@PathVariable int id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "admin/order-detail";
    }

    // Cập nhật trạng thái đơn hàng
    @PostMapping("/admin/orders/{id}/status")
    public String updateOrderStatus(@PathVariable int id, @RequestParam String newStatus) {
        orderService.updateOrderStatus(id, newStatus);
        return "redirect:/admin/orders";
    }
}
