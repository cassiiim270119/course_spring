package com.educandoweb.course.course.services;

import com.educandoweb.course.course.dto.CategoryDTO;
import com.educandoweb.course.course.dto.ProductCategoriesDTO;
import com.educandoweb.course.course.dto.ProductDTO;
import com.educandoweb.course.course.dto.UserDTO;
import com.educandoweb.course.course.entities.Category;
import com.educandoweb.course.course.entities.Product;
import com.educandoweb.course.course.entities.User;
import com.educandoweb.course.course.repositories.CategoryRepository;
import com.educandoweb.course.course.repositories.ProductRepository;
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
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public ProductDTO findById(Long id) {
        return productRepository.findById(id)
                .map(ProductDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public ProductDTO insert(ProductCategoriesDTO dto) {
        Product entity = dto.toEntity();
        setProductCategories(entity, dto.getCategories());
        return new ProductDTO(productRepository.save(entity));
    }

    @Transactional
    public ProductDTO update(Long id, ProductCategoriesDTO dto) {
        try {
            Product entity = productRepository.getOne(id);
            updateData(entity, dto);
            return new ProductDTO(productRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Product entity, ProductCategoriesDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        if (dto.getCategories() != null && !dto.getCategories().isEmpty()) {
            setProductCategories(entity, dto.getCategories());
        }
    }

    private void setProductCategories(Product entity, List<CategoryDTO> categories) {
        entity.getCategories().clear();
        categories.stream()
                .map(CategoryDTO::getId)
                .map(categoryRepository::getOne)
                .forEach(entity.getCategories()::add);
    }
}
