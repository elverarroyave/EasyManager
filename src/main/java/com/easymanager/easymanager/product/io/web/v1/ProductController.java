package com.easymanager.easymanager.product.io.web.v1;

import com.easymanager.easymanager.product.io.web.v1.model.ProductSaveRequest;
import com.easymanager.easymanager.config.MessageResponse;
import com.easymanager.easymanager.product.io.web.v1.model.ProductSaveResponse;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.ProductService;
import com.easymanager.easymanager.product.service.model.ProductSaveCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping("/api/v1/products")
@Api(tags = {"Products"}, value = "Product")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation(value = "Show all products.")
    public List<Product> showAll() {

        logger.debug("Begin find all products");

        List<Product> productsFound = productService.findAll();

        logger.debug("End find all products");

        return productsFound;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an Product for id.")
    public ResponseEntity<ProductSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){

        logger.debug("Begin findProductById: id ={}", id);

        Product productFound = productService.findById(id);

        logger.debug("End findProductById: productFound = {}", productFound);

        return ResponseEntity.ok(ProductSaveResponse.fromModel(productFound));
    }

    @PostMapping
    @ApiOperation(value = "Create an Product.")
    public ResponseEntity<Void> create(@RequestBody @Valid ProductSaveRequest productToCreate) {

        logger.debug("Begin create: productCreate = {}", productToCreate);

        ProductSaveCmd productToCreateCmd = ProductSaveRequest.toModel(productToCreate);

        Product productCreated = productService.register(productToCreateCmd);

        //El estandar de los create dice no devolver un objeto si no la localizacion
        URI location = fromUriString("/api/v1/products").path("/{id}")
                .buildAndExpand(productCreated.getId()).toUri();

        logger.debug("End create productCreated = {}", productCreated);

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete an Product.")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id){

        logger.debug("Begin delete: id = {}", id);

        productService.deleteById(id);

        logger.debug("End delete: id = {}", id);

        return new ResponseEntity<>(new MessageResponse("PRODUCTO ELIMINADO CORRECTAMENTE"), HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Update an Product for id.")
    public ResponseEntity<ProductSaveResponse> update(@PathVariable Long id,
                                                 @RequestBody @Valid ProductSaveRequest productToUpdate){

        logger.debug("Begin update: productToUpdate = {}, id = {}", productToUpdate, id);

        ProductSaveCmd productToUpdateCmd = ProductSaveRequest.toModel(productToUpdate);

        Product productUpdated = productService.update(id,productToUpdateCmd);

        logger.debug("End update: userUpdated = {}", productUpdated);

        return ResponseEntity.ok(ProductSaveResponse.fromModel(productUpdated));
    }

}
