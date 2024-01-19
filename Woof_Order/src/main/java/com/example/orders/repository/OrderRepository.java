package com.example.orders.repository;


import com.example.orders.model.Orders;
import com.example.orders.model.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    public Optional<Orders> findById(Long idx);

    public Optional<OrderDto> findByPhoneNumber(Long phoneNumber);
}


