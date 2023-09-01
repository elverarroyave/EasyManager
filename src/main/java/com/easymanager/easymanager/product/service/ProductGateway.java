package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ProductGateway {
    Product save(@NotNull Product productToCreate);

    Product findById(@NotNull Long id);

    void deleteById(@NotNull Long id);

    void deleteByCode(@NotNull String code);

    Product update(@NotNull Product productToUpdate);

    List<Product> findAll();

    Page<Product> findAllByPages(@NotNull Pageable pageable);

    Product findByCode(@NotNull String code);

    Optional<Product> verifyCode(@NotNull String code);

//    void updateStock(@NotNull int valor, Product product);

    List<Product> findProductsByName (String name);
}
