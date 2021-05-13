package com.easymanager.easymanager.sale.io.repository;

import com.easymanager.easymanager.sale.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

}
