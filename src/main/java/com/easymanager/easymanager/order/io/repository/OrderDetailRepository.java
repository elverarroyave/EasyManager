package com.easymanager.easymanager.order.io.repository;

import com.easymanager.easymanager.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
