package com.educandoweb.course.course.dto;

import com.educandoweb.course.course.entities.Product;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ProductCategoriesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private List<CategoryDTO> categories = new LinkedList<>();

    public ProductCategoriesDTO() {}

    public ProductCategoriesDTO(String name, String description, Double price, String imgUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductCategoriesDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }

    public Product toEntity() {
        return new Product(null, name, description, price, imgUrl);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
