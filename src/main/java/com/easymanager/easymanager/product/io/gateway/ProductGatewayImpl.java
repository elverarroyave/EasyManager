package com.easymanager.easymanager.product.io.gateway;

import com.easymanager.easymanager.config.exeption.InsufficientStock;
import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.product.io.repository.ProductRepository;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.ProductGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public class ProductGatewayImpl implements ProductGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String RESOURCE_NOT_FOUND = "Product not found";

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(@NotNull Product productToCreate) {

        logger.debug("Begin save productToCreate = {}", productToCreate);

        final Product productToBeCreated = productToCreate.toBuilder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        final Product productCreated = productRepository.save(productToBeCreated);

        logger.debug("End save userCreated = {}", productCreated);

        return productCreated;

    }

    @Override
    public Product findById(@NotNull Long id) {

        logger.debug("Begin findById id = {}", id);

        Product productFound = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption(("Id not found. Check your id please.")));

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
    public Product update(@NotNull Product productToUpdate) {

        logger.debug("Begin update productToUpdate = {}", productToUpdate);

        final Product productToBeUpdate = productToUpdate.toBuilder()
                .updateDate(LocalDateTime.now()).build();

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
    public Product findByCode(@NotNull String code) {

        Product productFound = productRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundExeption("Code not found. Please check your code"));

        return productFound;

    }

    @Override
    public void updateStock(@NotNull int valor, Product product) {
        int newStock = product.getStock()-valor;
        if(newStock < 0){
            throw new InsufficientStock("Insufficient stock in product: " + product.getName() + ", units available are: " + product.getStock());
        }
        product.setStock(newStock);
        productRepository.save(product);
    }
}
