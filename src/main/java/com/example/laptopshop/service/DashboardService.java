package com.example.laptopshop.service;

import com.example.laptopshop.repository.LaptopRepository;
import com.example.laptopshop.repository.OrderDetailRepository;
import com.example.laptopshop.repository.OrderRepository;
import com.example.laptopshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LaptopRepository laptopRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public long getTotalUsers() {
        return userRepository.count();
    }

    public long getTotalOrders() {
        return orderRepository.count();
    }

    public long getTotalStock() {
        return laptopRepository.count();
    }

    public double getTotalRevenue() {
        Double revenue = orderDetailRepository.getTotalRevenue();
        return revenue != null ? revenue : 0.0;
    }
    // Doanh thu hôm nay
    public double getRevenueToday() {
        Double revenueToday = orderDetailRepository.getRevenueToday(); // cần tự viết query
        return revenueToday != null ? revenueToday : 0.0;
    }

    // Lấy top 5 sản phẩm bán chạy
    public List<Object[]> getTop5Products() {
        return orderDetailRepository.findTop5BestSelling(); // query custom
    }
}
