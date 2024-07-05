package com.easymanager.easymanager.order.io.web.v1.model;


import com.easymanager.easymanager.order.model.OrderDetail;
import com.easymanager.easymanager.supplier.model.Supplier;
import com.easymanager.easymanager.user.model.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponse {

    private Long id;

    private int state;

    private String userId;

    private String supplierId;

    @OneToMany(
            mappedBy = "orden"
    )
    private List<OrderDetail> productsDetails = new ArrayList<>();

    private String details;

    private Long idPaymentMethod;

    private String bill;

    private boolean isCredit;

    private int paymentAmount;

    private LocalDateTime EstimatedDeliveryDate;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
