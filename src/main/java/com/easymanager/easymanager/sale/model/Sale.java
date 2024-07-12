package com.easymanager.easymanager.sale.model;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SALE")
@Table(name = "SALE")
public class Sale implements Serializable {

    private static final long serialVersionUID = 3648317578605854716L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Ralationship with Client
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    //Relationship with SaleDetail
    @OneToMany(mappedBy = "sale")
    private List<SaleDetail> productsDetail = new ArrayList<>();

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(nullable = true)
    private double subtotal;

    @Column(nullable = true)
    private double interestRate;

    private double total;

    @Column(nullable = true)
    private double remainingBalance;

    @Column(nullable = true)
    private double monthlyPayment;

    private Long paymentMethod;

    private boolean isCredit;

    private int paymentAmount;

    private String bill;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
