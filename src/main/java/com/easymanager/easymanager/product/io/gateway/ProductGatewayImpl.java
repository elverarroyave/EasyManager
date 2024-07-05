package com.easymanager.easymanager.product.io.gateway;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.product.io.repository.ProductRepository;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.ProductGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductGatewayImpl implements ProductGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String RESOURCE_NOT_FOUND = "Producto no encontrado";

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(@NotNull Product productToCreate) {

        logger.debug("Begin save productToCreate = {}", productToCreate);

        final Product productToBeCreated = productToCreate.toBuilder()
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        final Product productCreated = productRepository.save(productToBeCreated);

        logger.debug("End save userCreated = {}", productCreated);

        return productCreated;

    }

    @Override
    public Product findById(@NotNull Long id) {

        logger.debug("Begin findById id = {}", id);

        Product productFound = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption(("Producto con id: "+id+", no encontrado. Por favor revise el id.")));

        logger.debug("End findById productFound = {}", productFound);

        return productFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin deleteById id={}", id);

        findById(id);
        productRepository.deleteById(id);

        logger.debug("End deleteById id={}", id);
    }

    @Override
    public void deleteByCode(String code) {
        findByCode(code);
        productRepository.deleteByCode(code);
    }

    @Override
    public Product update(@NotNull Product productToUpdate) {

        logger.debug("Begin update productToUpdate = {}", productToUpdate);

        final Product productToBeUpdate = productToUpdate.toBuilder()
                .updatedDate(LocalDateTime.now()).build();

        final Product productUpdated = productRepository.save(productToBeUpdate);

        logger.debug("End update productToUpdate = {}", productToUpdate);

        return productUpdated;
    }

    @Override
    public List<Product> findAll() {

        logger.debug("Begin find all products");

        List<Product> productsFound = productRepository.findAll();

        logger.debug("Eng find all products");

        return productsFound;
    }

    @Override
    public Page<Product> findAllByPages(@NotNull Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    @Override
    public Product findByCode(@NotNull String code) {

        Product productFound = productRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundExeption("Código: "+code+", no encontrado. Por favor revisa el código."));
        return productFound;

    }

    @Override
    public Optional<Product> verifyCode(@NotNull String code) {
        return productRepository.findByCode(code);
    }


    @Override
    public List<Product> findProductsByName(String name) {
        return productRepository.findProductsByName(name.toUpperCase());
    }


}
