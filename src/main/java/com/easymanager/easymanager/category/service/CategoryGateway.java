package com.easymanager.easymanager.category.service;

import com.easymanager.easymanager.category.model.Category;

import java.util.List;

public interface CategoryGateway {
    Category save(Category categoryToCreate);
    Category update(Category categoryToUpdate);
    Category findById(Long id);
    List<Category> findAll();
    void deleteById(Long id);
}
