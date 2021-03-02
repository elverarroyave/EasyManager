package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.product.io.repository.ProductRepository;
import com.easymanager.easymanager.product.io.web.v1.model.ProductSaveRequest;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.mapper.ProductMapper;
import com.easymanager.easymanager.product.service.model.ProductSaveCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    ProductMapper productMapper = new ProductMapper();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Product register(@NotNull ProductSaveCmd productToCreateCmd) {

        logger.debug("Begin create productToCreateCmd = {}", productToCreateCmd);

        Product productToCreate = ProductSaveCmd.toModel(productToCreateCmd);

        Product productCreated = productRepository.save(productToCreate);

        return productCreated ;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(ProductSaveRequest productSaveRequest) {
        Product product = productMapper.productRequesToEntity(productSaveRequest);
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        if(!productRepository.existsById(id))
            throw new NotFoundExeption("Id not found. Check your id please.");
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        if(!productRepository.existsById(id))
            throw new NotFoundExeption("Id not found. Check your id please.");
        return productRepository.findById(id).get();
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findByCode(code);
    }


}
