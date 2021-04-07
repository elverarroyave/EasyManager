package com.easymanager.easymanager.sale.io.repository;

import com.easymanager.easymanager.sale.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM SALE s WHERE s.client = ?1")
    List<Sale> findByClientId(Long id);
}
