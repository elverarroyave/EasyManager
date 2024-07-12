package com.easymanager.easymanager.sale.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name="PAYMENT_SALE")
@Table
public class Payment implements Serializable {

    private static final long serialVersionUID = 4122582048203413912L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double remainingBalance;

    private double paymentAmount;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="FK_SALE")
    @JsonIgnore
    private Sale sale;

    private LocalDateTime paymentDate;

}
