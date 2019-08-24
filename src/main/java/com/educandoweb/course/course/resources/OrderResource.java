package com.educandoweb.course.course.resources;

import com.educandoweb.course.course.entities.Order;
import com.educandoweb.course.course.entities.User;
import com.educandoweb.course.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderResource {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }
}
