package com.easymanager.easymanager.order.io.gateway;

import com.easymanager.easymanager.order.io.repository.OrderDetailRepository;
import com.easymanager.easymanager.order.model.OrderDetail;
import com.easymanager.easymanager.order.service.orderDetail.OrderDetailsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class OrderDetailsGatewayImpl implements OrderDetailsGateway {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> saveAll(@NotNull List<OrderDetail> orderDetails) {

        List<OrderDetail> orderDetailsSaved = orderDetailRepository.saveAll(orderDetails);

        return orderDetailsSaved;
    }
}
