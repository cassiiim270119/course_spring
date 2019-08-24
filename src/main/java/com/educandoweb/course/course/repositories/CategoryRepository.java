package com.educandoweb.course.course.repositories;

import com.educandoweb.course.course.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
