package com.easymanager.easymanager.sale.service.sale;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientGateway;
import com.easymanager.easymanager.product.model.Product;
import com.easymanager.easymanager.product.service.ProductGateway;
import com.easymanager.easymanager.role.service.RoleGateway;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import com.easymanager.easymanager.sale.service.sale.model.Item;
import com.easymanager.easymanager.sale.service.saleDetails.SaleDetailsGateway;
import com.easymanager.easymanager.user.model.User;
import com.easymanager.easymanager.user.service.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{
    
    @Autowired
    private SaleGateway saleGateway;

    @Autowired
    private ClientGateway clientGateway;

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private SaleDetailsGateway saleDetailsGateway;

    @Autowired
    private UserGateway userGateway;

    @Autowired
    private RoleGateway roleGateway;
    
    @Override
    public Sale create(@NotNull String idClient, @NotNull List<Item> items) {


        // Usuario que realiza la venta
        User userVendedor = userGateway.findById(257L);

        // Verify
        if(!userVendedor.getRolesOfUser().contains(roleGateway.findById(1L))){
            throw new RuntimeException("No estas autorizado para realizar esta venta");
        }

        // Search client to will sale
        Client clientToSale =  clientGateway.findByDocument(idClient);

        // We create an list products details
        List<SaleDetail> productsDetail = new ArrayList<>();

        // Add items to the list products
        for (Item item : items) {
            SaleDetail productDetail = new SaleDetail();
            Product productInDataBase = productGateway.findById(item.getProductId());
            productDetail.setProduct(productInDataBase);
            productDetail.setAmount(item.getQuantity());
            productsDetail.add(productDetail);
            productGateway.updateStock(item.getQuantity(),productInDataBase);
        }

        // Persist productsDetails
        saleDetailsGateway.save(productsDetail);

        // Create void sale
        Sale saleCreate = new Sale();

        // Add attributes to sale
        saleCreate.setClient(clientToSale);
        saleCreate.setProductsDetail(productsDetail);

        // Add user to sale
        saleCreate.setUser(userVendedor);

        // Persist Sale
        Sale saleCreated = saleGateway.save(saleCreate);

        return saleCreated;
    }




}
