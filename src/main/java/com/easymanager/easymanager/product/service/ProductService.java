package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.product.model.Product;

import com.easymanager.easymanager.product.service.model.ProductSaveCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductService {

    Product register(@NotNull ProductSaveCmd userToCreateCmd);

    List<Product> findAll();

    Product update(@NotNull Long id, @NotNull ProductSaveCmd productToUpdateCmd);

    void deleteById(@NotNull Long id);

    Product findById(@NotNull Long id);

    Product findByCode(@NotNull String code);

    Page<Product> findAllByPages(@NotNull Pageable pageable);

    List<Product> findProductsByName(String name);

}
