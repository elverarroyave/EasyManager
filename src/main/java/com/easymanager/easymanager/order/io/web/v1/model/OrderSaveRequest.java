package com.easymanager.easymanager.order.io.web.v1.model;

import com.easymanager.easymanager.order.service.order.model.ItemOrder;
import com.easymanager.easymanager.sale.service.sale.model.Item;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class OrderSaveRequest {

    private Long supplierId;

    private Long idPaymentMethod;

    private String bill;

    private boolean isCredit;

    private int paymentAmount;

    private String details;

    private List<ItemOrder> productsDetails = new ArrayList<>();
}
