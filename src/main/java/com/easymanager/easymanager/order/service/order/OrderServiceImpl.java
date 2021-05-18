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
        User userToOrder = userGateway.findById(387L);

        // Verify
        if(!userToOrder.getRolesOfUser().contains(roleGateway.findById(1L))){
            throw new RuntimeException("No estas autorizado para realizar esta orden");
        }

        // We create an list products details
        List<OrderDetail> productsDetails = new ArrayList<>();

        //Add items to the list product
        for(ItemOrder itemOrder : itemOrders){
            OrderDetail productDetail = new OrderDetail();
            Product productInDataBase = productGateway.findById(itemOrder.getProductId());
            productDetail.setProduct(productInDataBase);
            productDetail.setAmount(itemOrder.getQuantity());
            productDetail.setNewPrice(itemOrder.getPrice());
            productsDetails.add(productDetail);
            productGateway.updateStock(itemOrder.getQuantity()*-1, productInDataBase);
        }

        // Persist product details
        orderDetailsGateway.saveAll(productsDetails);

        //Create order
        Orden orderToCreate = new Orden();

        // Add attributes to order
        orderToCreate.setDistributor(distributorToOrder);
        orderToCreate.setProductsDetails(productsDetails);

        // Add order to distributor
        orderToCreate.setUser(userToOrder);

        //Persist order
        Orden orderCreated = orderGateway.save(orderToCreate);

        return orderCreated;
    }
}
