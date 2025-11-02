package com.example.laptopshop.service;

import com.example.laptopshop.entity.Order;
import com.example.laptopshop.repository.OrderDetailRepository;
import com.example.laptopshop.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Lấy tất cả đơn hàng
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Lấy đơn hàng theo ID
    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng ID: " + id));
    }

    // Cập nhật trạng thái đơn hàng
    @Transactional
    public Order updateOrderStatus(int id, String newStatus) {
        Order order = getOrderById(id);
        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }

    // Doanh thu hôm nay
    public double getRevenueToday() {
        Double revenue = orderDetailRepository.getRevenueToday(LocalDate.now());
        return revenue != null ? revenue : 0.0;
    }

    // Tổng doanh thu
    public double getTotalRevenue() {
        Double total = orderDetailRepository.getTotalRevenue();
        return total != null ? total : 0.0;
    }

    // Top 5 sản phẩm bán chạy
    public List<Object[]> getTop5BestSelling() {
        List<Object[]> list = orderDetailRepository.findTop5BestSelling();
        return list.size() > 5 ? list.subList(0, 5) : list;
    }
}
