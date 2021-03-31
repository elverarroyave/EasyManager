package com.easymanager.easymanager.sale.service;

import com.easymanager.easymanager.sale.model.Sale;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SaleGateway {

    Sale save(@NotNull Sale saleToCreate);

    List<Sale> findAll();

    Sale findById(@NotNull Long id);

    Sale update(@NotNull Sale saleToUpdate);

    void deleteById(@NotNull Long id);

}
