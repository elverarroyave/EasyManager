package com.easymanager.easymanager.product.service;


import com.easymanager.easymanager.category.service.CategoryGateway;
import com.easymanager.easymanager.category.service.CategoryService;
import com.easymanager.easymanager.inventory.model.Inventory;
import com.easymanager.easymanager.inventory.service.InventoryGateway;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.model.ProductSaveCmd;
import com.easymanager.easymanager.tools.StringFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private ProductParameterValidation productParameterValidation;

    @Autowired
    private CategoryGateway categoryGateway;

    @Autowired
    private InventoryGateway inventoryGateway;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Product register(@NotNull ProductSaveCmd productToCreateCmd) {

        logger.debug("Begin create productToCreateCmd = {}", productToCreateCmd);

        Product productToCreate = ProductSaveCmd.toModel(productToCreateCmd);

        //TODO Validacion de categoria
        productToCreate.setCategory(categoryGateway.findById(productToCreateCmd.getCategory()));

        //Validacion de codigo unico de un producto
        productParameterValidation.codeValidation(productToCreate);

        Product productCreated = productGateway.save(productToCreate);
        Inventory inventory = Inventory.builder()
                .product(productCreated)
                .stock(0)
                .baseStock(0)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        inventoryGateway.create(inventory);

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
                .name(StringFormat.trim(productToUpdateCmd.getName()))
                .price(productToUpdateCmd.getPrice())
                .brand(StringFormat.trim(productToUpdateCmd.getBrand()))
                .model(StringFormat.trim(productToUpdateCmd.getModel()))
                .amountMountWarranty(productToUpdateCmd.getAmountMountWarranty())
                .height(productToUpdateCmd.getHeight())
                .width(productToUpdateCmd.getWidth())
                .depth(productToUpdateCmd.getDepth())
                .weight(productToUpdateCmd.getWeight())
                .color(StringFormat.trim(productToUpdateCmd.getColor()))
                .voltage(StringFormat.trim(productToUpdateCmd.getVoltage()))
                .code(StringFormat.trim(productToUpdateCmd.getCode()))
                .brand(StringFormat.trim(productToUpdateCmd.getBrand()))
                .description(StringFormat.trim(productToUpdateCmd.getDescription()))
                .category(categoryGateway.findById(productToUpdateCmd.getCategory()))
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
