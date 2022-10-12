package com.easymanager.easymanager.product.io.web.v1.model;

import com.easymanager.easymanager.product.service.model.ProductSaveCmd;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class ProductSaveRequest {

    @NotNull(message = "Es obligatorio ")
    @NotBlank(message = "No puede estar vacio ")
    private String name;

    @NotNull(message = "Es obligatorio ")
    @NotBlank(message = "No puede estar vacio ")
    private String code;

    @NotNull(message = "Es obligatorio ")
    @Min(value = 1, message = "For register an product, the min baseQuantity value is 1.")
    private int baseQuantity;

    @NotNull(message = "Es obligatorio ")
    @Min(value = 1, message = "For register an product, the min stock value is 1.")
    private int stock;

    private String brand;

    @Size(min = 3,max = 250, message = "La descripción debe ser entre 3 y 250 caracteres")
    private String description;

    @NotNull(message = "Es obligatorio ")
    @Min(value = 0, message = "El precio mínimo para el precio al costo es 0.")
    private double privatePrice;

    @NotNull(message = "Es obligatorio ")
    @Min(value = 0, message = "El precio mínimo para el precio al publico es 0.")
    private double publicPrice;

    private String category;

    public static ProductSaveCmd toModel(ProductSaveRequest productToCreate){
        return ProductSaveCmd.builder()
                .name(productToCreate.getName())
                .code(productToCreate.getCode())
                .baseQuantity(productToCreate.getBaseQuantity())
                .stock(productToCreate.getStock())
                .brand(productToCreate.getBrand())
                .description(productToCreate.getDescription())
                .privatePrice(productToCreate.getPrivatePrice())
                .publicPrice(productToCreate.getPublicPrice())
                .category(productToCreate.getCategory())
                .build();
    }
}
