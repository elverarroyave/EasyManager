package com.easymanager.easymanager.sale.model;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
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
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    //Ralationship with Client
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    //Relationship wiht SaleDetail
    @OneToMany
    @JoinColumn(name = "SALE_ID")
    private List<SaleDetail> productsDetail = new ArrayList<>();

}
