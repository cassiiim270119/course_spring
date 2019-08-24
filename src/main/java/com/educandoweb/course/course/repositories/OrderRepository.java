package com.educandoweb.course.course.repositories;

import com.educandoweb.course.course.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
