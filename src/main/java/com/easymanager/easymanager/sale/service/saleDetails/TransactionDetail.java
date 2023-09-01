package com.easymanager.easymanager.sale.service.saleDetails;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientGateway;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.ProductGateway;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import com.easymanager.easymanager.sale.service.sale.SaleGateway;
import com.easymanager.easymanager.sale.service.sale.model.Item;
import com.easymanager.easymanager.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetail {

    public List<SaleDetail> processProductList(List<Item> items, ProductGateway productGateway){
        List<SaleDetail> productsDetail = new ArrayList<>();
        for(Item item: items){
            Product productInDataBase = productGateway.findByCode(item.getCode());
            SaleDetail productDetail = new SaleDetail(
                    item.getQuantity(),
                    productInDataBase.getCode(),
                    productInDataBase.getName(),
                    productInDataBase.getPublicPrice(),
                    item.getQuantity()*productInDataBase.getPublicPrice()
            );
            productsDetail.add(productDetail);
//            productService.updateStock(item.getQuantity(), productInDataBase);
            productInDataBase.updateStock(-item.getQuantity());
        }
        return productsDetail;
    }

    public Sale makeSale(String clientDocument, User user, List<SaleDetail> saleDetails, ClientGateway clientGateway, SaleGateway saleGateway){
        Client clientToSale =  clientGateway.findByDocument(clientDocument);
        Sale saleCreate = Sale.builder()
                .client(clientToSale)
                .productsDetail(saleDetails)
                .user(user)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        saleGateway.save(saleCreate);
        return saleCreate;
    }
}
