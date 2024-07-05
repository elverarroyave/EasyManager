package com.easymanager.easymanager.product.io.web.v1.model;

import com.easymanager.easymanager.product.service.model.ProductSaveCmd;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Default;
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
    @Size(min = 3,max = 100, message = "El nombre debe ser entre 3 y 100 caracteres")
    private String name;

    @NotNull(message = "Es obligatorio ")
    @NotBlank(message = "No puede estar vacio ")
    @Size(min = 3,max = 50, message = "El código debe ser entre 3 y 50 caracteres")
    private String code;

    @NotNull
    @Size(max = 50, message = "La marca debe ser entre 3 y 50 caracteres")
    private String brand;

    @Size(min = 3,max = 1250, message = "La descripción debe ser entre 3 y 250 caracteres")
    private String description;

    private Long category;

    @Size(min = 3,max = 50, message = "El modelo debe ser entre 3 y 50 caracteres")
    private String model;

    @Min(value = 100, message = "El precio debe mayor a $100")
    private double price;

    @Min(value = 0, message = "La cantidad de montaje de garantía debe un valor positivo.")
    private int amountMountWarranty;

    @Min(value = 0, message = "La altura debe ser un valor positivo.")
    private double height;

    @Min(value = 0, message = "El ancho debe ser un valor positivo.")
    private double width;

    @Min(value = 0, message = "La profundidad debe ser un valor positivo.")
    private double depth;

    @Min(value = 0, message = "El peso debe ser un valor positivo.")
    private int weight;

    @Size(min = 3,max = 20, message = "El color debe ser entre 3 y 20 caracteres")
    private String color;

    private String voltage;

    public static ProductSaveCmd toModel(ProductSaveRequest productToCreate){
        return ProductSaveCmd.builder()
                .name(productToCreate.getName())
                .code(productToCreate.getCode())
                .brand(productToCreate.getBrand())
                .description(productToCreate.getDescription())
                .category(productToCreate.getCategory())
                .model(productToCreate.getModel())
                .amountMountWarranty(productToCreate.getAmountMountWarranty())
                .price(productToCreate.getPrice())
                .height(productToCreate.getHeight())
                .width(productToCreate.getWidth())
                .depth(productToCreate.getDepth())
                .weight(productToCreate.getWeight())
                .color(productToCreate.getColor())
                .voltage(productToCreate.getVoltage())
                .build();
    }
}
