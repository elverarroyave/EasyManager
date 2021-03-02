package com.easymanager.easymanager.product.model;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
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

}
