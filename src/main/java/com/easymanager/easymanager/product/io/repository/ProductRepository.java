package com.easymanager.easymanager.product.io.repository;

import com.easymanager.easymanager.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM PRODUCT p WHERE p.code = ?1")
    Optional<Product> findByCode(String code);

    @Query(value = "SELECT * FROM PRODUCT p WHERE UPPER(p.name) LIKE %:name% ORDER BY p.name ASC LIMIT 5", nativeQuery = true)
    List<Product> findProductsByName(String name);


}
