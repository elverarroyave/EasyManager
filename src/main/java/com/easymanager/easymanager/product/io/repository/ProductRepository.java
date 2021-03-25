package com.easymanager.easymanager.product.io.repository;

import com.easymanager.easymanager.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM PRODUCT p WHERE p.code = ?1")
    Optional<Product> findByCode(String code);

}
