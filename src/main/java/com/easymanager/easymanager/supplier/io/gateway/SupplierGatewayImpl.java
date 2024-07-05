package com.easymanager.easymanager.supplier.io.gateway;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.supplier.io.repository.SupplierRepository;
import com.easymanager.easymanager.supplier.model.Supplier;
import com.easymanager.easymanager.supplier.service.SupplierGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public class SupplierGatewayImpl implements SupplierGateway {

    @Autowired
    SupplierRepository supplierRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Supplier save(@NotNull Supplier supplierToSave) {

        Supplier supplierToBeSave = supplierToSave.toBuilder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Supplier supplierSaved = supplierRepository.save(supplierToBeSave);

        return supplierSaved;
    }

    @Override
    public Supplier findById(@NotNull Long id) {

        Supplier foundSupplier = supplierRepository.findById(id)
                .orElseThrow(()-> new NotFoundExeption("Distribuidor con id: "+id+", no encontrado. Por favor revisar."));

        return foundSupplier;
    }

    @Override
    public Supplier update(@NotNull Supplier supplierToUpdate) {

        Supplier supplierToBeUpdate = supplierToUpdate.toBuilder()
                .updateDate(LocalDateTime.now())
                .build();

        Supplier supplierUpdated = save(supplierToBeUpdate);

        return supplierUpdated;
    }

    @Override
    public List<Supplier> findAll() {

        List<Supplier> foundSuppliers = supplierRepository.findAll();

        return foundSuppliers;
    }

    @Override
    public void deleteById(@NotNull Long id) {

        supplierRepository.deleteById(id);

    }

    @Override
    public Supplier findByNit(@NotNull String nit) {

        Supplier foundSupplier = supplierRepository.findByNit(nit)
                .orElseThrow(()-> new NotFoundExeption("Nit: "+nit+", no encontrado. Por favor revisar."));

        return foundSupplier;
    }
}
