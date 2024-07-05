package com.easymanager.easymanager.order.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amount;

    private String codeProdcut;

    private String nameProduct;

    private double newPrice;

    private double totalOrder;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ORDEN_FK")
    @JsonIgnore
    private Orden orden;

    public OrderDetail(int amount, String codeProdcut, String nameProduct, double newPrice){
        this.amount = amount;
        this.codeProdcut = codeProdcut;
        this.nameProduct = nameProduct;
        this.newPrice = newPrice;
        this.totalOrder = amount * newPrice;
    }

}
