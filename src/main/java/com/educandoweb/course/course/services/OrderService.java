package com.educandoweb.course.course.services;

import com.educandoweb.course.course.dto.OrderDTO;
import com.educandoweb.course.course.dto.UserDTO;
import com.educandoweb.course.course.entities.Order;
import com.educandoweb.course.course.repositories.OrderRepository;
import com.educandoweb.course.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        return orderRepository.findById(id)
                .map(OrderDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
