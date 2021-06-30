package com.easymanager.easymanager.product.io.web.v1.model;

import com.easymanager.easymanager.product.model.Product;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSaveResponse extends RepresentationModel<Product> {

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

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

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
                .createDate(product.getCreateDate())
                .updateDate(product.getUpdateDate())
                .build();
    }

}
