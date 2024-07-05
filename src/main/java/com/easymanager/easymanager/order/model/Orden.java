package com.easymanager.easymanager.order.model;


import com.easymanager.easymanager.supplier.model.Supplier;
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
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(
            mappedBy = "orden"
    )
    private List<OrderDetail> productsDetails = new ArrayList<>();

    @Column(length = 1250)
    private String details;

    private Long idPaymentMethod;

    private String bill;

    @Column(columnDefinition = "boolean default false")
    private boolean isCredit;

    @Column(columnDefinition = "integer default 1")
    private int paymentAmount;

    private LocalDateTime EstimatedDeliveryDate;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}
