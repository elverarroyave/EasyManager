package com.easymanager.easymanager.product.io.web.v1.model;

import com.easymanager.easymanager.category.model.Category;
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

    private String brand;

    private String description;

    private Category category;

    private String model;

    private int amountMountWarranty;

    private double price;

    private double height;

    private double width;

    private double depth;

    private double weight;

    private String color;

    private String voltage;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public static ProductSaveResponse fromModel(Product product){
        return ProductSaveResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .brand(product.getBrand())
                .description(product.getDescription())
                .category(product.getCategory())
                .model(product.getModel())
                .amountMountWarranty(product.getAmountMountWarranty())
                .price(product.getPrice())
                .height(product.getHeight())
                .width(product.getWidth())
                .depth(product.getDepth())
                .weight(product.getWeight())
                .color(product.getColor())
                .voltage(product.getVoltage())
                .createdDate(product.getCreatedDate())
                .updatedDate(product.getUpdatedDate())
                .build();
    }

}
