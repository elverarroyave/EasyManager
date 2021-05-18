package com.easymanager.easymanager.sale.service.sale;

import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.service.sale.model.Item;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SaleService {

    Sale create(@NotNull String idClient, @NotNull List<Item> listProducts);

}
