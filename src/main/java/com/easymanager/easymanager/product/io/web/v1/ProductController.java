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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping("/api/v1/products")
@Api(tags = {"Products"}, value = "Product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation(value = "Get all products.")
    public ResponseEntity<CollectionModel<Product>> getAllProducts() {

        logger.debug("Begin find all products");

        List<Product> productsFound = productService.findAll();

        productsFound.forEach(product -> {
            product.add(linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel());
        });

        Link allProductsLink = linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel();

        logger.debug("End find all products");

        return ResponseEntity.ok(CollectionModel.of(productsFound, allProductsLink));
    }

    @GetMapping("/productsByPages")
    @ApiOperation(value = "Show all products by pages.")
    public ResponseEntity<Page<Product>> findAllByPages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "false") boolean asc
    ){
        Page<Product> productsFound = productService.findAllByPages(
                PageRequest.of(page,size, Sort.by(order).descending())
        );
        if(!asc)
            productsFound = productService.findAllByPages(
                    PageRequest.of(page,size, Sort.by(order).ascending())
            );

        productsFound.forEach(product -> {
            product.add(linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel());
        });

        return new ResponseEntity<Page<Product>>(productsFound, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Find an Product for id.")
    public ResponseEntity<CollectionModel<ProductSaveResponse>> findById(@Valid @PathVariable("id") @NotNull Long id){

        logger.debug("Begin findProductById: id ={}", id);

        Product productFound = productService.findById(id);

        ProductSaveResponse productFoundResponse = ProductSaveResponse.fromModel(productFound);

        productFoundResponse.add(linkTo(methodOn(ProductController.class).findById(productFound.getId())).withSelfRel());

        logger.debug("End findProductById: productFound = {}", productFound);

        Link allProductsLink = linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel();

        List<ProductSaveResponse> products = new ArrayList<>();

        products.add(productFoundResponse);

        return ResponseEntity.ok(CollectionModel.of( products, allProductsLink ));
    }

    @GetMapping("/code/{code}")
    @ApiOperation(value = "Find an Product for code.")
    public ResponseEntity<CollectionModel<ProductSaveResponse>> findByCode(@Valid @PathVariable("code") @NotNull String code){

        logger.debug("Begin findProductByCode: code ={}", code);

        Product productFound = productService.findByCode(code);

        ProductSaveResponse productFoundResponse = ProductSaveResponse.fromModel(productFound);

        productFoundResponse.add(linkTo(methodOn(ProductController.class).findById(productFound.getId())).withSelfRel());

        Link allprductsLink = linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel();

        logger.debug("End findProductByCode: productFound = {}", productFound);

        List<ProductSaveResponse> products = new ArrayList<>();
        products.add(productFoundResponse);

        return ResponseEntity.ok(CollectionModel.of( products,allprductsLink));
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
