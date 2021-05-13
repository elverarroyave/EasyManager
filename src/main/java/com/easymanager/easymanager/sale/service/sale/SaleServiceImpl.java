package com.easymanager.easymanager.sale.service.sale;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientGateway;
import com.easymanager.easymanager.product.service.ProductGateway;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import com.easymanager.easymanager.sale.service.model.Item;
import com.easymanager.easymanager.sale.service.saleDetails.SaleDetailsGateway;
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

    @Autowired
    SaleDetailsGateway saleDetailsGateway;
    
    @Override
    public Sale create(@NotNull String idClient, @NotNull List<Item> items) {

        // We create an list products details
        List<SaleDetail> productsDetail = new ArrayList<>();

        // Add items to the list products
        for (Item item : items) {
            SaleDetail productDetail = new SaleDetail();
            productDetail.setProduct(productGateway.findById(item.getProductId()));
            productDetail.setAmount(item.getQuantity());
            productsDetail.add(productDetail);
        }

        // Persist productsDetails
        saleDetailsGateway.save(productsDetail);

        // Search client to will sale
        Client clientToSale =  clientGateway.findByDocument(idClient);

        // Create void sale
        Sale saleCreate = new Sale();

        // Add attributes to sale
        saleCreate.setClient(clientToSale);
        saleCreate.setProductsDetail(productsDetail);

        // Persist Sale
        Sale saleCreted = saleGateway.save(saleCreate);

        return saleCreted;
    }




}
