package com.easymanager.easymanager.payment_method.io.repository;

import com.easymanager.easymanager.payment_method.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
