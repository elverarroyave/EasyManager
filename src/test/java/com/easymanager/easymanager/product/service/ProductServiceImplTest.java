package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.product.io.repository.ProductRepository;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.model.ProductSaveCmd;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImplTest {

    @Autowired(required = false)
    ProductService productService;

    @Test
    @Order(1)
    void register() {

        //Given
        ProductSaveCmd productTest = new ProductSaveCmd(
                "   NAME TEST   ",
                " CODE_TEST ",
                100,
                100,
                "BRAND_TEST    ",
                "DESCRIPCION TEST          ",
                50000,
                80000,
                "CATEGORIA TEST"
        );
        Product productCreated = productService.register(productTest);

        //When
        Product productFound = productService.findById(productCreated.getId());

        //Then
        assertThat(productFound.getName()).isEqualTo("NAME TEST");
        assertThat(productFound.getCode()).isEqualTo("CODE_TEST");
        assertThat(productFound.getBrand()).isEqualTo("BRAND_TEST");
        assertThat(productFound.getDescription()).isEqualTo("DESCRIPCION TEST");
        assertThat(productFound.getStock()).isEqualTo(100);
        assertThat(productFound.getBaseQuantity()).isEqualTo(100);
        assertThat(productFound.getPrivatePrice()).isEqualTo(50000);
        assertThat(productFound.getPublicPrice()).isEqualTo(80000);


    }

    @Test
    @Order(2)
    void findAll() {
        List<Product> products = productService.findAll();
        assertThat(products).isNotNull();
    }

    @Test
    @Order(3)
    void update() {
        Product productFound = productService.findByCode("CODE_TEST");
        ProductSaveCmd productToUpdate = new ProductSaveCmd(
                "NUEVO NOMBRE DE PRODUCTO  ",
                "CODE_TEST",
                99,
                99,
                "  NUEVA MARCA DE PRODUCTO  ",
                "  NUEVA DESCRIPCINO DE PRODUCTO  ",
                99000,
                10000,
                "NEUVA CATEGORIA DE PRODUCTO  "
        );

        Product productUpdate = productService.update(
                productFound.getId(),
                productToUpdate
        );

        assertThat(productUpdate.getName()).isEqualTo("NUEVO NOMBRE DE PRODUCTO");
        assertThat(productUpdate.getBrand()).isEqualTo("NUEVA MARCA DE PRODUCTO");
        assertThat(productUpdate.getDescription()).isEqualTo("NUEVA DESCRIPCINO DE PRODUCTO");
        assertThat(productUpdate.getCategory()).isEqualTo("NEUVA CATEGORIA DE PRODUCTO");
        assertThat(productUpdate.getBaseQuantity()).isEqualTo(99);
        assertThat(productUpdate.getStock()).isEqualTo(99);
        assertThat(productUpdate.getPrivatePrice()).isEqualTo(99000);
        assertThat(productUpdate.getPublicPrice()).isEqualTo(10000);
    }

    @Test
    @Order(4)
    void findById() {
        Product product = productService.findById(1L);
        assertThat(product.getId()).isEqualTo(1L);
        assertThrows(NotFoundExeption.class, ()->{
            productService.findById(9999L);
        });
    }

    @Test
    @Order(5)
    void findByCode() {
        Product product = productService.findByCode("TP132156");
        assertThat(product.getCode()).isEqualTo("TP132156");
        assertThrows(NotFoundExeption.class, ()->{
            productService.findByCode("CA99999");
        });
    }

    @Test
    @Order(6)
    void findAllByPages() {
    }

    @Test
    @Order(7)
    void findProductsByName() {
        List<Product> produts = productService.findProductsByName("bo");
        assertThat(produts.size()).isEqualTo(4);

        produts = productService.findProductsByName("co");
        assertThat(produts.size()).isEqualTo(4);

        produts = productService.findProductsByName("");
        assertThat(produts.size()).isEqualTo(5);

        produts = productService.findProductsByName("PRODUCTO NO SE ENCUENTRA");
        assertThat(produts.size()).isEqualTo(0);
    }

    @Test
    @Order(8)
    void deleteByCode() {

        Product productFound = productService.findByCode("CODE_TEST");
        assertThat(productFound.getName()).isEqualTo("NUEVO NOMBRE DE PRODUCTO");

        productService.deleteByCode("CODE_TEST");

        assertThrows(NotFoundExeption.class, ()-> {
            productService.findByCode("CODE_TEST");
        });
    }
}
