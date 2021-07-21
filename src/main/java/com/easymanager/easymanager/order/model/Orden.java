package com.easymanager.easymanager.order.model;


import com.easymanager.easymanager.distributor.model.Distributor;
import com.easymanager.easymanager.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private int state;

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "FK_USER")
    private User user;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "DISTRIBUTOR_ID")
    private Distributor distributor;

    @OneToMany(
            mappedBy = "orden"
    )
    private List<OrderDetail> productsDetails = new ArrayList<>();

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}
