package com.easymanager.easymanager.sale.model;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.user.model.User;
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

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(
            mappedBy = "sale",
            cascade = CascadeType.ALL)
    private List<SaleDetail> productsDetail = new ArrayList<>();

    public Sale(Long id, Client client) {
        this.client = client;
        this.id = id;
    }

}
