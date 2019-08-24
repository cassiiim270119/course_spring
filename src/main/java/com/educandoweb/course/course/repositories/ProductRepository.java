package com.educandoweb.course.course.repositories;

import com.educandoweb.course.course.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
