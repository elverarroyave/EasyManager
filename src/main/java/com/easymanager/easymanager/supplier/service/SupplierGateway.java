package com.easymanager.easymanager.supplier.service;

import com.easymanager.easymanager.supplier.model.Supplier;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SupplierGateway {

    Supplier save(@NotNull Supplier supplierToSave);

    Supplier findById(@NotNull Long id);

    Supplier update(@NotNull Supplier supplierToUpdate);

    List<Supplier> findAll();

    void deleteById(@NotNull Long id);

    Supplier findByNit(@NotNull String nit);

}
