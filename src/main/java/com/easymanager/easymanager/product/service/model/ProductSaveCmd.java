package com.easymanager.easymanager.product.service.model;

import com.easymanager.easymanager.product.io.web.v1.model.ProductSaveRequest;
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
    @NotNull(message = "Es obligatorio ")
    @NotBlank(message = "No puede estar vacio ")
    private String name;

    @NotNull(message = "Es obligatorio ")
    @NotBlank(message = "No puede estar vacio ")
    private String code;

    @NotNull(message = "Es obligatorio ")
    private int baseQuantity;

    @NotNull(message = "Es obligatorio ")
    private int stock;

    private String brand;

    @Size(min = 3,max = 250, message = "La descripcion debe ser entre 3 y 250 caracteres")
    private String description;

    @NotNull(message = "Es obligatorio ")
    @Min(value = 0, message = "El precio mínimo es 0.")
    private double privatePrice;

    @NotNull(message = "Es obligatorio ")
    @Min(value = 0, message = "El precio mínimo es 0.")
    private double publicPrice;

    private String category;

    public static Product toModel(ProductSaveCmd productToCreateCmd){
        return Product.builder()
                .name(productToCreateCmd.getName())
                .code(productToCreateCmd.getCode())
                .baseQuantity(productToCreateCmd.getBaseQuantity())
                .stock(productToCreateCmd.getStock())
                .brand(productToCreateCmd.getBrand())
                .description(productToCreateCmd.getDescription())
                .privatePrice(productToCreateCmd.getPrivatePrice())
                .publicPrice(productToCreateCmd.getPublicPrice())
                .category(productToCreateCmd.getCategory())
                .build();
    }
}
