package com.easymanager.easymanager.sale.service;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientGateway;
import com.easymanager.easymanager.product.service.ProductGateway;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import com.easymanager.easymanager.sale.service.model.ItemDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{
    
    @Autowired
    SaleGateway saleGateway;

    @Autowired
    ClientGateway clientGateway;

    @Autowired
    ProductGateway productGateway;
    
    @Override
    public Sale create(@NotNull String idClient, @NotNull List<ItemDetail> listProducts) {

        Sale saleToCreate = new Sale();

        List<SaleDetail> productsDetail = new ArrayList<>();

        for (ItemDetail itemDetail : listProducts) {
            SaleDetail productDetail = new SaleDetail();
            productDetail.setProduct(productGateway.findById(itemDetail.getProductId()));
            productDetail.setAmount(itemDetail.getQuantity());
            productDetail.setSale(saleToCreate);

            productsDetail.add(productDetail);
        }

        Client clientToSale =  clientGateway.findByDocument(idClient);

        saleToCreate.setClient(clientToSale);
        saleToCreate.setProductsDetail(productsDetail);

        Sale saleCreted = saleGateway.save(saleToCreate);

        return saleCreted;
    }
}
