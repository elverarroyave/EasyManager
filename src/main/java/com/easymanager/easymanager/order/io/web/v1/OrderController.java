package com.easymanager.easymanager.order.io.web.v1;

import com.easymanager.easymanager.order.io.web.v1.model.OrderSaveRequest;
import com.easymanager.easymanager.order.model.Orden;
import com.easymanager.easymanager.order.service.order.OrderService;
import com.easymanager.easymanager.order.service.order.model.ItemOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping("/api/v1/orders")
@Api(tags = "Orders", value = "Orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping
    @ApiOperation(value = "Create a order")
    public ResponseEntity<Void> create(@RequestBody OrderSaveRequest orderSaveRequest){

        Orden orderCreated = orderService.create(orderSaveRequest);

        URI location = fromUriString("/api/v1/sales").path("/{id}")
                .buildAndExpand(orderCreated.getId()).toUri();

        return ResponseEntity.created(location).build();

    }


}
