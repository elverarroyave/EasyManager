package com.easymanager.easymanager.supplier.service;

import com.easymanager.easymanager.supplier.model.Supplier;
import com.easymanager.easymanager.supplier.service.model.SupplierSaveCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierGateway distributorGateway;


    @Override
    public Supplier save(@NotNull SupplierSaveCmd supplierSaveCmd) {

        Supplier supplierToSave = SupplierSaveCmd.toModel(supplierSaveCmd);

        Supplier supplierSaved = distributorGateway.save(supplierToSave);

        return supplierSaved;
    }

    @Override
    public Supplier findById(@NotNull Long id) {

        Supplier supplierFound = distributorGateway.findById(id);

        return supplierFound;
    }

    @Override
    public Supplier update(@NotNull Long id, SupplierSaveCmd supplierSaveCmd) {

        Supplier supplierInDataBase = distributorGateway.findById(id);

        Supplier supplierToUpdate = supplierInDataBase.toBuilder()
                .name(supplierSaveCmd.getName())
                .nit(supplierSaveCmd.getNit())
                .numberPhone(supplierSaveCmd.getNumberPhone())
                .email(supplierSaveCmd.getEmail())
                .address(supplierSaveCmd.getAddress())
                .build();

        Supplier supplierUpdated = distributorGateway.save(supplierToUpdate);

        return supplierUpdated;
    }

    @Override
    public List<Supplier> findAll() {

        List<Supplier> distributorsFound = distributorGateway.findAll();

        return distributorsFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        distributorGateway.deleteById(id);
    }

    @Override
    public Supplier findByNit(@NotNull String nit) {

        Supplier supplierFound = distributorGateway.findByNit(nit);

        return supplierFound;
    }
}
