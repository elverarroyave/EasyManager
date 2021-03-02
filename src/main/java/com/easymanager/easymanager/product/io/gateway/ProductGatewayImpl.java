package com.easymanager.easymanager.product.io.gateway;

import com.easymanager.easymanager.product.io.repository.ProductRepository;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.ProductGateway;
import com.easymanager.easymanager.product.service.model.ProductQuerySearcCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public class ProductGatewayImpl implements ProductGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String RESOURCE_NOT_FOUND = "Product not found";

    private ProductRepository productRepository;

    public ProductGatewayImpl(ProductRepository productRepository){this.productRepository = productRepository;}

    @Override
    public Product save(@NotNull Product productToCreate) {

        logger.debug("Begin save productToCreate = {}", productToCreate);

        final Product productCreated = productRepository.save(productToCreate);

        logger.debug("End save userCreated = {}", productCreated);

        return productCreated;

    }

    @Override
    public Product findById(@NotNull Long id) {
        return null;
    }

    @Override
    public Page<Product> findByParameters(@NotNull ProductQuerySearcCmd queryCriteria, @NotNull Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(@NotNull Long id) {

    }

    @Override
    public Product update(@NotNull Product productToUpdate) {
        return null;
    }
}
