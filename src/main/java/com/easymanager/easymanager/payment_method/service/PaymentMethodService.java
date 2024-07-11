package com.easymanager.easymanager.payment_method.service;

import com.easymanager.easymanager.payment_method.io.repository.PaymentMethodRepository;
import com.easymanager.easymanager.payment_method.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PaymentMethodService {
    //getAllPaymentMethod
    List<PaymentMethod> getPaymentMethods();
}
