package com.example.laptopshop.repository;

import com.example.laptopshop.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
    Laptop findByLaptopCode(String laptopCode);
}
