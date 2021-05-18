package com.easymanager.easymanager.distributor.io.repository;

import com.easymanager.easymanager.distributor.model.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    @Query("SELECT d FROM DISTRIBUTOR d WHERE d.nit = ?1")
    Optional<Distributor> findByNit(String nit);
}
