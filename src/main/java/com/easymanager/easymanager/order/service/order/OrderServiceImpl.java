package com.easymanager.easymanager.order.service.order;

import com.easymanager.easymanager.inventory.model.Inventory;
import com.easymanager.easymanager.inventory.service.InventoryGateway;
import com.easymanager.easymanager.order.io.web.v1.model.OrderSaveRequest;
import com.easymanager.easymanager.supplier.model.Supplier;
import com.easymanager.easymanager.supplier.service.SupplierGateway;
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


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderGateway orderGateway;

    @Autowired
    private OrderDetailsGateway orderDetailsGateway;

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private SupplierGateway supplierGateway;

    @Autowired
    private UserGateway userGateway;

    @Autowired
    private RoleGateway roleGateway;

    @Autowired
    private InventoryGateway inventoryGateway;

    @Override
    public Orden create(OrderSaveRequest orderToCreate) {

        // Search supplier to will order
        Supplier supplierToOrder = supplierGateway.findById(orderToCreate.getSupplierId());

        //User to receive order
        User userToOrder = userGateway.findById(999L);

        // Verify
        if(!userToOrder.getRolesOfUser().contains(roleGateway.findById(4L))){
            throw new RuntimeException("No estas autorizado para recepcionar esta orden");
        }

        // We create a list products details
        List<OrderDetail> productsDetails = new ArrayList<>();

        //Add items to the list product
        for(ItemOrder itemOrder : orderToCreate.getProductsDetails()){
            Product productInDataBase = productGateway.findByCode(itemOrder.getCode());
            OrderDetail productDetail = new OrderDetail(
                    itemOrder.getQuantity(),
                    productInDataBase.getCode(),
                    productInDataBase.getName(),
                    itemOrder.getPrice()
            );
            productsDetails.add(productDetail);
            //update stock in inventory
            Inventory inventory = inventoryGateway.findByProductId(productInDataBase.getId());
            inventory.updateStock(itemOrder.getQuantity());
        }

        //Create order
        Orden newOrder = new Orden();

        // Add attributes to order
        newOrder.setSupplier(supplierToOrder);
        newOrder.setProductsDetails(productsDetails);
        newOrder.setState(1);//Añadimos el estado inicial de la orden.
        newOrder.setBill(orderToCreate.getBill());
        newOrder.setDetails(orderToCreate.getDetails());
        newOrder.setCredit(orderToCreate.isCredit());
        newOrder.setPaymentAmount(orderToCreate.getPaymentAmount());
        newOrder.setIdPaymentMethod(orderToCreate.getIdPaymentMethod());
        //TODO: Añadir la funcionalidad para crear la factura de la orden

        // Add order to user
        newOrder.setUser(userToOrder);

        //Persist order
        Orden orderCreated = orderGateway.save(newOrder);

        // Persist product details
        productsDetails.forEach(productDetail->productDetail.setOrden(orderCreated));
        orderDetailsGateway.saveAll(productsDetails);

        return orderCreated;
    }
}
