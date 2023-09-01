package com.easymanager.easymanager.sale.model;


import com.easymanager.easymanager.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "SALE_DETAIL")
@Table
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amount;

    private String codeProduct;

    private String nameProduct;

    private double publicPriceProduct;

    private double totalSale;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="FK_SALE")
    @JsonIgnore
    private Sale sale;

    public SaleDetail(int amount, String codeProduct, String nameProduct, double publicPriceProduct, double totalSale) {
        this.amount = amount;
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.publicPriceProduct = publicPriceProduct;
        this.totalSale = totalSale;
    }

    public SaleDetail(){}
}
