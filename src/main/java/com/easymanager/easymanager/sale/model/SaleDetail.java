package com.easymanager.easymanager.sale.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "SALE_DETAIL")
@Table
public class SaleDetail implements Serializable {

    private static final long serialVersionUID = 2844622109359081864L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amount;

    private Long idProduct;

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

    public SaleDetail(int amount, Long idProduct, String nameProduct, double publicPriceProduct, double totalSale) {
        this.amount = amount;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.publicPriceProduct = publicPriceProduct;
        this.totalSale = totalSale;
    }

    public SaleDetail(){}
}
