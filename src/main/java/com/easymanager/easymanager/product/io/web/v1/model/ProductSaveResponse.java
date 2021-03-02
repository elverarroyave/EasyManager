package com.easymanager.easymanager.product.io.web.v1.model;

import com.easymanager.easymanager.product.model.Product;
import lombok.*;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSaveResponse {

    private Long id;

    private String name;

    private String code;

    private int baseQuantity;

    private int stock;

    private String brand;

    private String description;

    private double privatePrice;

    private double publicPrice;

    private String category;

    public static ProductSaveResponse fromModel(Product product){
        return ProductSaveResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .baseQuantity(product.getBaseQuantity())
                .stock(product.getStock())
                .brand(product.getBrand())
                .description(product.getDescription())
                .privatePrice(product.getPrivatePrice())
                .publicPrice(product.getPublicPrice())
                .category(product.getCategory())
                .build();
    }

}
