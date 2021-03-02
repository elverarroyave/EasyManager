package com.easymanager.easymanager.product.service.mapper;

import com.easymanager.easymanager.product.io.web.v1.model.ProductSaveRequest;
import com.easymanager.easymanager.product.model.Product;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    private ModelMapper mapper = new ModelMapper();

    public Product productRequesToEntity(ProductSaveRequest productSaveRequest){
        return mapper.map(productSaveRequest, Product.class);
    }
}
