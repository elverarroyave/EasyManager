package com.easymanager.easymanager.payment_method.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;

@Entity
@Table(name = "PAYMENT_METHOD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private boolean state;
    private double totalAmount;
    private double limitAmount;
    private double handlingFee;
    private DateFormat cutoutDate;
    private DateFormat paymentDate;
    private DateFormat createDate;
    private DateFormat updateDate;
}
