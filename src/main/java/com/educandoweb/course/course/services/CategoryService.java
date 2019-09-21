package com.educandoweb.course.course.services;

import com.educandoweb.course.course.dto.CategoryDTO;
import com.educandoweb.course.course.entities.Category;
import com.educandoweb.course.course.repositories.CategoryRepository;
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
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        return categoryRepository.findById(id).map(CategoryDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public CategoryDTO insert(CategoryDTO category) {
        return new CategoryDTO(categoryRepository.save(category.toEntity()));
    }

    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO category) {
        try {
            Category entity = categoryRepository.getOne(id);
            entity.setName(category.getName());
            return new CategoryDTO(categoryRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
