package com.easymanager.easymanager.product.io.web.v1;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.product.io.repository.ProductRepository;
import com.easymanager.easymanager.product.io.web.v1.model.ProductSaveRequest;
import com.easymanager.easymanager.product.io.web.request.ProductResponse;
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

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @ApiOperation(value = "Show all products.")
    public List<Product> allProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an Product for id.")
    public ResponseEntity<ProductSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){

        logger.debug("Beging findProductById: id ={}", id);

        Product productFound = productService.findById(id);

        logger.debug("End findProductById: productFound = {}", productFound);

        return ResponseEntity.ok(ProductSaveResponse.fromModel(productFound));
    }


    @PostMapping
    @ApiOperation(value = "Create an Product.")
    public ResponseEntity<Void> addNewProduct(@RequestBody @Valid ProductSaveRequest productToCreate){

        logger.debug("Begin create: productCreate = {}", productToCreate);

        ProductSaveCmd productToCreateCmd = ProductSaveRequest.toModel(productToCreate);

        Product productCreated = productService.register(productToCreateCmd);

        URI location = fromUriString("/api/v1/products").path("/{id}")
                .buildAndExpand(productCreated.getId()).toUri();

        logger.debug("End create productCreated = {}", productCreated);

        return ResponseEntity.created(location).build();
    }

    /*

    @PutMapping("{id}")
    @ApiOperation(value = "Update an Product.")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductSaveRequest productSaveRequest){

        if(!productRepository.existsById(id)) throw new NotFoundExeption("Id not found, check your id please*");

        productSaveRequest.setId(id);
        return new ResponseEntity<>(productService.update(productSaveRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete an Product.")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>(new ProductResponse("PRODUCTO ELIMINADO CORRECTAMENTE"), HttpStatus.OK);
    }

    */

}
