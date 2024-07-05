package com.easymanager.easymanager.supplier.io.repository;

import com.easymanager.easymanager.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT d FROM SUPPLIER d WHERE d.nit = ?1")
    Optional<Supplier> findByNit(String nit);
}
