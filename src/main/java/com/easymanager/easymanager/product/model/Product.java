package com.easymanager.easymanager.product.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PRODUCT")
@Table(name = "PRODUCT")
public class Product extends RepresentationModel<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public void updateStock(int value){
        stock +=value;
    }

}
