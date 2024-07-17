package com.easymanager.easymanager.sale.service.saleDetails;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.ClientGateway;
import com.easymanager.easymanager.config.ProductExceptionController;
import com.easymanager.easymanager.config.exeption.InsufficientStock;
import com.easymanager.easymanager.inventory.model.Inventory;
import com.easymanager.easymanager.inventory.service.InventoryGateway;
import com.easymanager.easymanager.product.service.ProductGateway;
import com.easymanager.easymanager.sale.io.web.v1.model.SaleSaveRequest;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import com.easymanager.easymanager.sale.service.sale.SaleGateway;
import com.easymanager.easymanager.sale.service.sale.model.Item;
import com.easymanager.easymanager.tools.DoubleUtil;
import com.easymanager.easymanager.user.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class TransactionDetail {

    public List<SaleDetail> processProductList(SaleSaveRequest saleSaveRequest, ProductGateway productGateway, InventoryGateway inventoryGateway, float currentMonthlyInterest){
        List<SaleDetail> productsDetail = new ArrayList<>();
        List<Item> items = saleSaveRequest.getItems();
        for(Item item: items){
            Inventory inventoryProduct = inventoryGateway.findByProductCode(item.getCode());
            //Verificar stock
            if(inventoryProduct.getStock() < item.getQuantity()){
                throw new InsufficientStock("No hay suficiente stock para el producto: " + inventoryProduct.getProduct().getName());
            }
            SaleDetail productDetail = new SaleDetail(
                    item.getQuantity(),
                    inventoryProduct.getProduct().getId(),
                    inventoryProduct.getProduct().getName(),
                    inventoryProduct.getProduct().getPrice(),
                    item.getQuantity()*inventoryProduct.getProduct().getPrice()*currentMonthlyInterest*saleSaveRequest.getPaymentAmount()
            );
            productsDetail.add(productDetail);
            inventoryProduct.updateStock(-item.getQuantity());
        }
        return productsDetail;
    }

    public Sale buildSale(SaleSaveRequest saleSaveRequest, User user, List<SaleDetail> saleDetails, float currentMonthlyInterest, ClientGateway clientGateway, SaleGateway saleGateway){
        Client clientToSale =  clientGateway.findByDocument(saleSaveRequest.getClientNumDocument());
        double subTotal = saleDetails.stream().mapToDouble(SaleDetail::getTotalSale).sum();
        double interestRate = 0;
        if(saleSaveRequest.isCredit()){
            interestRate = subTotal * currentMonthlyInterest * saleSaveRequest.getPaymentAmount();
        }
        double total = subTotal + interestRate;
        double monthlyPayment = total / (saleSaveRequest.getPaymentAmount() + 1);
        double remainingBalance = total - saleSaveRequest.getFirstPayment();
        Sale saleCreate = Sale.builder()
                .client(clientToSale)
                .productsDetail(saleDetails)
                .user(user)
                .isCredit(saleSaveRequest.isCredit())
                .paymentAmount(saleSaveRequest.getPaymentAmount())
                .paymentMethod(saleSaveRequest.getPaymentMethod())
                .subtotal(subTotal)
                .interestRate(DoubleUtil.roundDecimal(interestRate,2))
                .monthlyPayment(DoubleUtil.roundDecimal(monthlyPayment,2))
                .total(DoubleUtil.roundDecimal(total,2))
                .remainingBalance(DoubleUtil.roundDecimal(remainingBalance,2))
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        return saleCreate;
    }


}
