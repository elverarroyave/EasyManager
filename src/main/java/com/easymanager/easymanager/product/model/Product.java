package com.easymanager.easymanager.product.model;

import com.easymanager.easymanager.category.model.Category;
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

    private String brand;

    @Column(length = 1250)
    private String description;

    private String model;

    @Column(columnDefinition = "integer default 0")
    private double price;

    @Column(columnDefinition = "integer default 0")
    private int amountMountWarranty;
    @Column(columnDefinition = "integer default 0")
    private double height;
    @Column(columnDefinition = "integer default 0")
    private double width;
    @Column(columnDefinition = "integer default 0")
    private double depth;
    @Column(columnDefinition = "integer default 0")
    private double weight;

    private String color;

    private String voltage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "id")
    private Category category;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
