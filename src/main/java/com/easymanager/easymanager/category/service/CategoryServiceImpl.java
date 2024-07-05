package com.easymanager.easymanager.category.service;


import com.easymanager.easymanager.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryGateway categoryGateway;


    @Override
    public Category saveCategory(Category categoryToCreate) {
        return categoryGateway.save(categoryToCreate);
    }

    @Override
    public Category updateCategory(Category categoryToUpdate) {
        return categoryGateway.update(categoryToUpdate);
    }

    @Override
    public Category findById(Long id) {
        return categoryGateway.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryGateway.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryGateway.deleteById(id);
    }
}
