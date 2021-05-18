package com.easymanager.easymanager.order.service.order.model;

import lombok.Data;

@Data
public class ItemOrder {

    private Long productId;

    private int quantity;

    private double price;
}
