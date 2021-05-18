package com.easymanager.easymanager.product.service;

import com.easymanager.easymanager.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProductParameterValidation {

    @Autowired
    private ProductGateway productGateway;

    //Code validation
    boolean codeValidation(Product product){
        return productGateway.findByCode(product.getCode()) == null;
    }

    public ProductParameterValidation(){}
}
