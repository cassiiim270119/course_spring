package com.educandoweb.course.course.repositories;

import com.educandoweb.course.course.entities.Order;
import com.educandoweb.course.course.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
