package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.config.exeption.BadRequestExeption;
import com.easymanager.easymanager.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ProductParameterValidation {

    @Autowired
    private ProductGateway productGateway;

    //Code validation
    void codeValidation(Product product){
        if(productGateway.verifyCode(product.getCode()).isPresent())
            throw new BadRequestExeption("This code is already used by other product");
    }

    public ProductParameterValidation(){}
}
