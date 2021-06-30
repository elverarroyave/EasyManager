package com.easymanager.easymanager.sale.service.sale;

import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.service.sale.model.Item;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SaleService {

    Sale create(@NotNull String numDocument, @NotNull List<Item> listProducts);

    List<Sale> findByDateRange(@NotNull  String initDate, String finalDate);

}
