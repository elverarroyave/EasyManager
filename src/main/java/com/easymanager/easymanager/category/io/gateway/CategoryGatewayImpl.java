package com.easymanager.easymanager.category.io.gateway;

import com.easymanager.easymanager.category.io.repository.CategoryRepository;
import com.easymanager.easymanager.category.model.Category;
import com.easymanager.easymanager.category.service.CategoryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryGatewayImpl implements CategoryGateway {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category categoryToCreate) {
        return categoryRepository.save(categoryToCreate);
    }

    @Override
    public Category update(Category categoryToUpdate) {
        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
