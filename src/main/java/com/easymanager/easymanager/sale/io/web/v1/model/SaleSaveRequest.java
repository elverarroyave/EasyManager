package com.easymanager.easymanager.sale.io.web.v1.model;

import com.easymanager.easymanager.sale.service.sale.model.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class SaleSaveRequest {

    private String clientNumDocument;

    private boolean isCredit;

    private int paymentAmount;

    private Long paymentMethod;

    private List<Item> items;

}
