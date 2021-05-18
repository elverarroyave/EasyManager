package com.easymanager.easymanager.order.model;


import com.easymanager.easymanager.distributor.model.Distributor;
import com.easymanager.easymanager.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Entity
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameDistributor;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "distributor_id")
    @JsonBackReference
    private Distributor distributor;

    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private List<OrderDetail> productsDetails = new ArrayList<>();

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Boolean state;
}
