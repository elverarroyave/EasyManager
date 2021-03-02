package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.product.io.web.v1.model.ProductSaveRequest;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.model.ProductSaveCmd;

import java.util.List;

public interface ProductService {

    Product register(ProductSaveCmd productSaveRequest);

    List<Product> findAll();

    Product update(ProductSaveRequest productSaveRequest);

    void delete(Long id);

    Product findById(Long id);

    Product findByCode(String code);
}
