package com.educandoweb.course.course.services;

import com.educandoweb.course.course.dto.CategoryDTO;
import com.educandoweb.course.course.dto.ProductCategoriesDTO;
import com.educandoweb.course.course.dto.ProductDTO;
import com.educandoweb.course.course.entities.Category;
import com.educandoweb.course.course.entities.Product;
import com.educandoweb.course.course.repositories.CategoryRepository;
import com.educandoweb.course.course.repositories.ProductRepository;
import com.educandoweb.course.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private void setProductCategories(Product entity, List<CategoryDTO> categories) {
        entity.getCategories().clear();
        categories.stream()
                .map(CategoryDTO::getId)
                .map(categoryRepository::getOne)
                .forEach(entity.getCategories()::add);
    }
}
