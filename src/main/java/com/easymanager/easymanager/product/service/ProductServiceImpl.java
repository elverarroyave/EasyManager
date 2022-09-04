package com.easymanager.easymanager.product.service;


import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.model.ProductSaveCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private ProductParameterValidation productParameterValidation;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Product register(@NotNull ProductSaveCmd productToCreateCmd) {

        logger.debug("Begin create productToCreateCmd = {}", productToCreateCmd);

        Product productToCreate = ProductSaveCmd.toModel(productToCreateCmd);

        //Validacion de codigo unico de un producto
        productParameterValidation.codeValidation(productToCreate);

        Product productCreated = productGateway.save(productToCreate);

        logger.debug("End create userCreated = {}", productCreated);

        return productCreated;
    }

    @Override
    public List<Product> findAll() {

        logger.debug("Begin find all products");

        List<Product> productsFound = productGateway.findAll();

        logger.debug("End find all products");

        return productsFound;
    }

    @Override
    public Product update(@NotNull Long id,@NotNull ProductSaveCmd productToUpdateCmd) {

        logger.debug("Begin update id={}", productToUpdateCmd);

        Product productInDatabase = findById(id);

        Product productToUpdate = productInDatabase.toBuilder()
                .name(productToUpdateCmd.getName().trim())
                .code(productToUpdateCmd.getCode().trim())
                .baseQuantity(productToUpdateCmd.getBaseQuantity())
                .stock(productToUpdateCmd.getStock())
                .brand(productToUpdateCmd.getBrand().trim())
                .description(productToUpdateCmd.getDescription().trim())
                .privatePrice(productToUpdateCmd.getPrivatePrice())
                .publicPrice(productToUpdateCmd.getPublicPrice())
                .category(productToUpdateCmd.getCategory().trim())
                .build();

        if(!productInDatabase.getCode().equalsIgnoreCase(productToUpdateCmd.getCode()))
            productParameterValidation.codeValidation(productToUpdate);

        Product productUpdated = productGateway.update(productToUpdate);

        logger.debug("End update userUpdated = {}", productUpdated);

        return productUpdated;
    }

    @Override
    public void deleteById(@NotNull Long id) {

        logger.debug("Begin deleteById id={}", id);

        productGateway.deleteById(id);

        logger.debug("End deleteById id={}", id);
    }

    @Override
    public void deleteByCode(String code) {
        logger.debug("Begin deleteByCode code={}", code);

        productGateway.deleteByCode(code);

        logger.debug("End deleteByCode code={}", code);
    }


    @Override
    @Transactional(readOnly = true)
    public Product findById(@NotNull Long id) {

        logger.debug("Begin findById id = {}", id);

        Product productFound =productGateway.findById(id);

        logger.debug("End findById id = {}", id);

        return productFound;
    }

    @Override
    public Product findByCode(@NotNull String code) {

        Product productFound = productGateway.findByCode(code);

        return productFound;
    }

    @Override
    public Page<Product> findAllByPages(@NotNull Pageable pageable) {
        return productGateway.findAllByPages(pageable);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return productGateway.findProductsByName(name);
    }

}
