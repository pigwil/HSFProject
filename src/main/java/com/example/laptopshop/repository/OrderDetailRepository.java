package com.example.laptopshop.repository;

import com.example.laptopshop.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    // Tổng doanh thu hôm nay
    @Query("SELECT SUM(od.totalPrice) FROM OrderDetail od WHERE od.order.createdDate = :today")
    Double getRevenueToday(LocalDate today);

    // Tổng doanh thu
    @Query("SELECT SUM(od.totalPrice) FROM OrderDetail od")
    Double getTotalRevenue();

    @Query("SELECT od.laptop.laptopName, SUM(od.quantity) as totalQty " +
            "FROM OrderDetail od " +
            "GROUP BY od.laptop.laptopName " +
            "ORDER BY totalQty DESC")
    List<Object[]> findTop5BestSelling();

    @Query("SELECT SUM(od.totalPrice) FROM OrderDetail od WHERE od.order.createdDate = CURRENT_DATE")
    Double getRevenueToday();

}
