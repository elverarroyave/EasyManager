package com.easymanager.easymanager.sale.service.saleDetails;

import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.model.SaleDetail;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SaleDetailsGateway {
    List<SaleDetail> save(@NotNull List<SaleDetail> saleDetail);

    void deleteSalesDetails(@NotNull Sale sale);
}
