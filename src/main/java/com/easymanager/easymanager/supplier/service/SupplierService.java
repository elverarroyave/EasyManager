package com.easymanager.easymanager.supplier.service;

import com.easymanager.easymanager.supplier.model.Supplier;
import com.easymanager.easymanager.supplier.service.model.SupplierSaveCmd;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface SupplierService {

    Supplier save(@NotNull SupplierSaveCmd supplierSaveCmd);

    Supplier findById(@NotNull Long id);

    Supplier update(@NotNull Long id, SupplierSaveCmd supplierSaveCmd);

    List<Supplier> findAll();

    void deleteById(@NotNull Long id);

    Supplier findByNit(@NotNull String nit);

}
