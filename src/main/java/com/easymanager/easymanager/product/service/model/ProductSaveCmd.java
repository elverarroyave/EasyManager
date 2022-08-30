package com.easymanager.easymanager.product.service.model;

import com.easymanager.easymanager.product.model.Product;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class ProductSaveCmd {

    private String name;

    private String code;

    private int baseQuantity;

    private int stock;

    private String brand;

    private String description;

    private double privatePrice;

    private double publicPrice;

    private String category;

    public static Product toModel(ProductSaveCmd productToCreateCmd){
        return Product.builder()
                    .name(productToCreateCmd.getName().trim())
                    .code(productToCreateCmd.getCode().trim())
                    .baseQuantity(productToCreateCmd.getBaseQuantity())
                    .stock(productToCreateCmd.getStock())
                    .brand(productToCreateCmd.getBrand().trim())
                    .description(productToCreateCmd.getDescription().trim())
                    .privatePrice(productToCreateCmd.getPrivatePrice())
                    .publicPrice(productToCreateCmd.getPublicPrice())
                    .category(productToCreateCmd.getCategory())
                    .build();
        }
}
