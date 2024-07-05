package com.easymanager.easymanager.order.service.order;

import com.easymanager.easymanager.order.io.web.v1.model.OrderSaveRequest;
import com.easymanager.easymanager.order.model.Orden;
import com.easymanager.easymanager.order.service.order.model.ItemOrder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface OrderService {

    Orden create(OrderSaveRequest orderToCreate);

}
