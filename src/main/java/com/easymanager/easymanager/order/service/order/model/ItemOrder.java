package com.easymanager.easymanager.order.service.order.model;

import lombok.Data;

@Data
public class ItemOrder {

    private String code;

    private int quantity;

    private double newPrice;
}
