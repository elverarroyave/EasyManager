package com.easymanager.easymanager.sale.io.web.v1.model;

import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;
import lombok.Builder;
import lombok.Data;

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

    private SaleClientResponse client;

    public static SaleResponse fromModel(Sale saleToResponse){
        SaleClientResponse clientResponse = new SaleClientResponse(
                saleToResponse.getClient().getId(),
                saleToResponse.getClient().getNumDocument(),
                saleToResponse.getClient().getName(),
                saleToResponse.getClient().getLastName(),
                saleToResponse.getClient().getNumPhone(),
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
                .build();
    }

    public static List<SaleResponse> fromModelList(List<Sale> salesToResponse){
        List<SaleResponse> salesModelResponse = new ArrayList<>();
        salesToResponse.forEach(sale -> salesModelResponse.add(fromModel(sale)));
        return salesModelResponse;
    }
}
