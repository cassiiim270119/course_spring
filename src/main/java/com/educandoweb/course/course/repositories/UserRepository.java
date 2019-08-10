package com.educandoweb.course.course.repositories;

import com.educandoweb.course.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
