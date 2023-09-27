package com.Mymicroservice.orders_services.repositories;

import com.Mymicroservice.orders_services.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}