package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.model.ProductQuerySearcCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface ProductGateway {
    Product save(@NotNull Product productToCreate);

    Product findById(@NotNull Long id);

    Page<Product> findByParameters(@NotNull ProductQuerySearcCmd queryCriteria, @NotNull Pageable pageable);

    void deleteById(@NotNull Long id);

    Product update(@NotNull Product productToUpdate);
}
