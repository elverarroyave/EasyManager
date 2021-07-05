package com.easymanager.easymanager.sale.io.repository;

import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM SALE_DETAIL s WHERE s.sale = ?1")
    void deleteSalesDetails(@NotNull Sale user);
}
