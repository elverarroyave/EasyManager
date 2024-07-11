package com.easymanager.easymanager.payment_method.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENT_METHOD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = -4811764975399910259L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private boolean state;
    private double totalAmount;
    private double limitAmount;
    private double handlingFee;
    private LocalDateTime cutoutDate;
    private LocalDateTime paymentDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
