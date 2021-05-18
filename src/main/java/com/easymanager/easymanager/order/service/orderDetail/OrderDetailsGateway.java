package com.easymanager.easymanager.order.service.orderDetail;

import com.easymanager.easymanager.order.model.OrderDetail;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderDetailsGateway {
    List<OrderDetail> saveAll(@NotNull List<OrderDetail> orderDetails);
}
