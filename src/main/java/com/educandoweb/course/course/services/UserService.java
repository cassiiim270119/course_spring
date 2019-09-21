package com.educandoweb.course.course.services;

import com.educandoweb.course.course.dto.UserDTO;
import com.educandoweb.course.course.dto.UserInsertDTO;
import com.educandoweb.course.course.entities.User;
import com.educandoweb.course.course.repositories.UserRepository;
import com.educandoweb.course.course.services.exceptions.DatabaseException;
import com.educandoweb.course.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(UserDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public UserDTO insert(UserInsertDTO user) {
        return new UserDTO(userRepository.save(user.toEntity()));
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public UserDTO update(Long id, UserDTO user) {
        try {
            User entity = userRepository.getOne(id);
            updateData(entity, user);
            return new UserDTO(userRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, UserDTO user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }
}
