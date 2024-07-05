package com.easymanager.easymanager.product.service.model;

import com.easymanager.easymanager.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveCmd {

    private String name;

    private String code;

    private int baseQuantity;

    private int stock;

    private String brand;

    private String description;

    private Long category;

    private String model;

    private int amountMountWarranty;

    private double price;

    private double height;

    private double width;

    private double depth;

    private double weight;

    private String color;

    private String voltage;

    public static Product toModel(ProductSaveCmd productToCreateCmd){
        return Product.builder()
                    .name(productToCreateCmd.getName().trim())
                    .code(productToCreateCmd.getCode().trim())
                    .brand(productToCreateCmd.getBrand().trim())
                    .description(productToCreateCmd.getDescription().trim())
                    .model(productToCreateCmd.getModel().trim())
                    .price(productToCreateCmd.getPrice())
//                    .category(productToCreateCmd.getCategory())
                    .amountMountWarranty(productToCreateCmd.getAmountMountWarranty())
                    .height(productToCreateCmd.getHeight())
                    .width(productToCreateCmd.getWidth())
                    .depth(productToCreateCmd.getDepth())
                    .weight(productToCreateCmd.getWeight())
                    .color(productToCreateCmd.getColor().trim())
                    .voltage(productToCreateCmd.getVoltage().trim())
                    .build();
        }
}
