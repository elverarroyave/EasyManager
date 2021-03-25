package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductGateway {
    Product save(@NotNull Product productToCreate);

    Product findById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    Product update(@NotNull Product productToUpdate);

    List<Product> findAll();
}
