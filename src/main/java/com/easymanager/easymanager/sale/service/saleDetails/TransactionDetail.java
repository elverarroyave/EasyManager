package com.easymanager.easymanager.sale.service.saleDetails;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientGateway;
import com.easymanager.easymanager.inventory.model.Inventory;
import com.easymanager.easymanager.inventory.service.InventoryGateway;
import com.easymanager.easymanager.product.service.ProductGateway;
import com.easymanager.easymanager.sale.io.web.v1.model.SaleSaveRequest;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import com.easymanager.easymanager.sale.service.sale.SaleGateway;
import com.easymanager.easymanager.sale.service.sale.model.Item;
import com.easymanager.easymanager.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class TransactionDetail {

    public List<SaleDetail> processProductList(List<Item> items, ProductGateway productGateway, InventoryGateway inventoryGateway){
        List<SaleDetail> productsDetail = new ArrayList<>();
        for(Item item: items){
            Inventory inventoryProduct = inventoryGateway.findByProductCode(item.getCode());
            SaleDetail productDetail = new SaleDetail(
                    item.getQuantity(),
                    inventoryProduct.getProduct().getId(),
                    inventoryProduct.getProduct().getName(),
                    inventoryProduct.getProduct().getPrice(),
                    item.getQuantity()*inventoryProduct.getProduct().getPrice()
            );
            productsDetail.add(productDetail);
            inventoryProduct.updateStock(-item.getQuantity());
        }
        return productsDetail;
    }

    public Sale buildSale(SaleSaveRequest saleSaveRequest, User user, List<SaleDetail> saleDetails, ClientGateway clientGateway, SaleGateway saleGateway){
        Client clientToSale =  clientGateway.findByDocument(saleSaveRequest.getClientNumDocument());
        Sale saleCreate = Sale.builder()
                .client(clientToSale)
                .productsDetail(saleDetails)
                .user(user)
                .isCredit(saleSaveRequest.isCredit())
                .paymentAmount(saleSaveRequest.getPaymentAmount())
                .paymentMethod(saleSaveRequest.getPaymentMethod())
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        return saleCreate;
    }
}
