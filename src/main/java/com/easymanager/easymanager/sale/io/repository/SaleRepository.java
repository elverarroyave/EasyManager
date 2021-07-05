package com.easymanager.easymanager.sale.io.repository;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.sale.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM SALE s WHERE s.client = ?1")
    List<Sale> findByClientId(Client client);

    @Query("SELECT s FROM SALE s WHERE ?1 < s.createDate AND s.createDate < ?2")
    List<Sale> findByDateRange(LocalDateTime initDate, LocalDateTime finalDate);
}
