package com.easymanager.easymanager.sale.service.sale;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientGateway;
import com.easymanager.easymanager.config.exeption.BadRequestExeption;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    public Sale create(@NotNull String numDocument, @NotNull List<Item> items) {


        // Usuario que realiza la venta
        //TODO se debe crear funcionalidad para recupera el usuario que realiza la venta
        User userVendedor = userGateway.findById(18L);

        // Verify
        if(!userVendedor.getRolesOfUser().contains(roleGateway.findById(1L))){
            throw new BadRequestExeption("Usuario no autorizado para realizar la venta");
        }

        // Search client to will sale
        Client clientToSale =  clientGateway.findByDocument(numDocument);

        // We create an list products details
        List<SaleDetail> productsDetail = new ArrayList<>();

        // Add items to the list products
        for (Item item : items) {
            Product productInDataBase = productGateway.findByCode(item.getCode());
            //productDetail.setProduct(productInDataBase);
            SaleDetail productDetail = new SaleDetail(
                    item.getQuantity(),
                    productInDataBase.getCode(),
                    productInDataBase.getName(),
                    productInDataBase.getPublicPrice(),
                    item.getQuantity()*productInDataBase.getPublicPrice()
            );

            productsDetail.add(productDetail);
            productGateway.updateStock(item.getQuantity(),productInDataBase);
        }

        // Create void sale
        Sale saleCreate = new Sale();

        // Add attributes to sale
        saleCreate.setClient(clientToSale);
        saleCreate.setProductsDetail(productsDetail);

        // Add user to sale
        saleCreate.setUser(userVendedor);

        // Persist Sale
        Sale saleCreated = saleGateway.save(saleCreate);

        // Persist productsDetails
        productsDetail.forEach(productDetail->productDetail.setSale(saleCreated));
        saleDetailsGateway.save(productsDetail);

        return saleCreated;
    }

    public List<Sale> findByDateRange(@NotNull String initDate, String finalDate){

        String[] date1 = initDate.split("-");

        String[] date2 = finalDate.split("-");

        LocalDateTime localDateTime1 = LocalDateTime.of(
                Integer.parseInt(date1[0]),
                Integer.parseInt(date1[1]),
                Integer.parseInt(date1[2]),
                00,00);

        LocalDateTime localDateTime2 = LocalDateTime.of(
                Integer.parseInt(date2[0]),
                Integer.parseInt(date2[1]),
                Integer.parseInt(date2[2]),
                23,59,59);

        return saleGateway.findByDateRange(localDateTime1,localDateTime2);
    }

    @Override
    public Sale findById(@NotNull long id) {
        Sale saleFound = saleGateway.findById(id);
        return saleFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {

        Sale saleToDelete = saleGateway.findById(id);

        saleDetailsGateway.deleteSalesDetails(saleToDelete);
        saleGateway.deleteById(id);
    }

    @Override
    public List<Sale> findByClientId(@NotNull Long id) {

        Client clientFound = clientGateway.findById(id);

        List<Sale> salesFound = saleGateway.findByClientId(clientFound);

        return salesFound;
    }

}
