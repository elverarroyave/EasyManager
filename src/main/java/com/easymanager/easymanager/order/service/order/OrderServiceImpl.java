package com.easymanager.easymanager.order.service.order;

import com.easymanager.easymanager.distributor.model.Distributor;
import com.easymanager.easymanager.distributor.service.DistributorGateway;
import com.easymanager.easymanager.order.model.Orden;
import com.easymanager.easymanager.order.model.OrderDetail;
import com.easymanager.easymanager.order.service.order.model.ItemOrder;
import com.easymanager.easymanager.order.service.orderDetail.OrderDetailsGateway;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.ProductGateway;
import com.easymanager.easymanager.role.service.RoleGateway;
import com.easymanager.easymanager.user.model.User;
import com.easymanager.easymanager.user.service.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderGateway orderGateway;

    @Autowired
    private OrderDetailsGateway orderDetailsGateway;

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private DistributorGateway distributorGateway;

    @Autowired
    private UserGateway userGateway;

    @Autowired
    private RoleGateway roleGateway;

    @Override
    public Orden create(@NotNull String nit, @NotNull List<ItemOrder> itemOrders) {

        // Search distributor to will order
        Distributor distributorToOrder = distributorGateway.findByNit(nit);

        //User to receive order
        User userToOrder = userGateway.findById(1586L);

        // Verify
        if(!userToOrder.getRolesOfUser().contains(roleGateway.findById(34L))){
            throw new RuntimeException("No estas autorizado para realizar esta orden");
        }

        // We create an list products details
        List<OrderDetail> productsDetails = new ArrayList<>();

        //Add items to the list product
        for(ItemOrder itemOrder : itemOrders){
            Product productInDataBase = productGateway.findByCode(itemOrder.getCode());
            OrderDetail productDetail = new OrderDetail(
                    itemOrder.getQuantity(),
                    productInDataBase.getCode(),
                    productInDataBase.getName(),
                    productInDataBase.getPrivatePrice(),
                    itemOrder.getNewPrice()
            );
            productsDetails.add(productDetail);
            productGateway.updateStock(itemOrder.getQuantity()*-1, productInDataBase);
        }

        //Create order
        Orden orderToCreate = new Orden();

        // Add attributes to order
        orderToCreate.setDistributor(distributorToOrder);
        orderToCreate.setProductsDetails(productsDetails);
        orderToCreate.setState(1);//Añadimos el estado inicial de la orden.

        // Add order to distributor
        orderToCreate.setUser(userToOrder);

        //Persist order
        Orden orderCreated = orderGateway.save(orderToCreate);

        // Persist product details
        productsDetails.forEach(productDetail->productDetail.setOrden(orderCreated));
        orderDetailsGateway.saveAll(productsDetails);

        return orderCreated;
    }
}
