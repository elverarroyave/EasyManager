package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.product.io.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductServiceImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void register() {
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByCode() {
    }

    @Test
    void findAllByPages() {
    }

    @Test
    void findProductsByName() {
    }
}
