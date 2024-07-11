package com.easymanager.easymanager.payment_method.io.web.v1.model;

import com.easymanager.easymanager.payment_method.model.PaymentMethod;
import com.easymanager.easymanager.payment_method.service.PaymentMethodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paymentMethods")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentMethodController {

    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping
    @ApiOperation("Find al paymentMethods")
    public ResponseEntity<List<PaymentMethod>> findAllPaymentMethods() {
        List<PaymentMethod> paymentMethods =  paymentMethodService.getPaymentMethods();
        return ResponseEntity.ok(paymentMethods);
    }
}
