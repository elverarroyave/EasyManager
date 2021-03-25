package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.product.model.Product;

import com.easymanager.easymanager.product.service.model.ProductSaveCmd;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductService {

    Product register(@NotNull ProductSaveCmd userToCreateCmd);

    List<Product> findAll();

    Product update(@NotNull Long id, @NotNull ProductSaveCmd productToUpdateCmd);

    void deleteById(@NotNull Long id);

    Product findById(@NotNull Long id);

}
