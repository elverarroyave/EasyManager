package com.easymanager.easymanager.sale.model;


import com.easymanager.easymanager.product.model.Product;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private int amount;

    public double getPrice(){
        return (product.getPublicPrice()*amount);
    }

}
