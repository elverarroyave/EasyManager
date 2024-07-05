package com.easymanager.easymanager.category.service;

import com.easymanager.easymanager.category.model.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category categoryToCreate);
    Category updateCategory(Category categoryToUpdate);
    Category findById(Long id);
    List<Category> findAll();
    void deleteById(Long id);
}
