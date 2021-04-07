package com.easymanager.easymanager.sale.service;

import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.service.model.ItemDetail;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SaleService {

    Sale create(@NotNull String idClient, @NotNull List<ItemDetail> listProducts);

}
