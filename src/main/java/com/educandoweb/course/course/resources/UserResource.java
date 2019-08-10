package com.educandoweb.course.course.resources;

import com.educandoweb.course.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User user = new User(1L, "Maria", "maria@gmail.com", "424324234", "1234");
        return ResponseEntity.ok(Collections.singletonList(user));
    }
}
