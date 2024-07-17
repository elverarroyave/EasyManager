package com.easymanager.easymanager.sale.io.web.v1.model;

import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class SaleResponse {

    private Long id;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private List<SaleDetail> productsDetail = new ArrayList<>();

    private SaleUserResponse user;

    private double subtotal;

    private double interestRate;

    private double total;

    private double remainingBalance;

    private double monthlyPayment;

    private Long paymentMethod;

    private boolean isCredit;

    private int paymentAmount;

    private String bill;

    private SaleClientResponse client;

    public static SaleResponse fromModel(Sale saleToResponse){
        SaleClientResponse clientResponse = new SaleClientResponse(
                saleToResponse.getClient().getId(),
                saleToResponse.getClient().getNumDocument(),
                saleToResponse.getClient().getFirstName(),
                saleToResponse.getClient().getLastName(),
                saleToResponse.getClient().getNumberPhone(),
                saleToResponse.getClient().getAddress()
        );
        SaleUserResponse userResponse = new SaleUserResponse(
                saleToResponse.getUser().getId(),
                saleToResponse.getUser().getName(),
                saleToResponse.getUser().getNumDocument()
        );
        return SaleResponse.builder()
                .id(saleToResponse.getId())
                .createDate(saleToResponse.getCreateDate())
                .updateDate(saleToResponse.getUpdateDate())
                .productsDetail(saleToResponse.getProductsDetail())
                .user(userResponse)
                .client(clientResponse)
                .subtotal(saleToResponse.getSubtotal())
                .interestRate(saleToResponse.getInterestRate())
                .total(saleToResponse.getTotal())
                .remainingBalance(saleToResponse.getRemainingBalance())
                .monthlyPayment(saleToResponse.getMonthlyPayment())
                .paymentMethod(saleToResponse.getPaymentMethod())
                .isCredit(saleToResponse.isCredit())
                .paymentAmount(saleToResponse.getPaymentAmount())
                .build();
    }

    public static List<SaleResponse> fromModelList(List<Sale> salesToResponse){
        List<SaleResponse> salesModelResponse = new ArrayList<>();
        salesToResponse.forEach(sale -> salesModelResponse.add(fromModel(sale)));
        return salesModelResponse;
    }
}
