package com.example.laptopshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(
            name = "laptop_code",
            referencedColumnName = "laptop_code",
            nullable = false
    )
    private Laptop laptop;

    @Column(name = "quantity")
    private int quantity;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "total_price")
    private double totalPrice;
}
