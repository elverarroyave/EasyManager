package com.easymanager.easymanager.order.io.repository;

import com.easymanager.easymanager.order.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orden, Long> {
}
