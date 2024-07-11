package com.easymanager.easymanager.payment_method.service;

import com.easymanager.easymanager.payment_method.io.repository.PaymentMethodRepository;
import com.easymanager.easymanager.payment_method.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService{

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }
}
