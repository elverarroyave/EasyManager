package com.easymanager.easymanager.sale.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

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

    @Column(columnDefinition = "double precision default 0")
    private double subTotal;

    @Column(columnDefinition = "double precision default 0")
    private double interest;

    private double totalSale;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="FK_SALE")
    @JsonIgnore
    private Sale sale;

    public SaleDetail(int amount, Long idProduct, String nameProduct, double publicPriceProduct, double interest) {
        this.amount = amount;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.publicPriceProduct = publicPriceProduct;
        this.subTotal = amount * publicPriceProduct;
        this.interest = interest;
        this.totalSale = subTotal + interest;
    }

    public SaleDetail(){}
}
