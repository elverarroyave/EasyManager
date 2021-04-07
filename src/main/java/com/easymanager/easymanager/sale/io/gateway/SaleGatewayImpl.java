package com.easymanager.easymanager.sale.io.gateway;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.sale.io.repository.SaleRepository;
import com.easymanager.easymanager.sale.model.Sale;
import com.easymanager.easymanager.sale.service.SaleGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SaleGatewayImpl implements SaleGateway {

    @Autowired
    SaleRepository saleRepository;

    @Override
    public Sale save(@NotNull Sale saleToCreate) {

        Sale saleToBeCreate = saleToCreate.toBuilder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now()).build();

        Sale saleCreated = saleRepository.save(saleToBeCreate);

        return saleCreated;
    }

    @Override
    public List<Sale> findAll() {

        List<Sale> salesFound = saleRepository.findAll();

        return salesFound;
    }

    @Override
    public Sale findById(@NotNull Long id) {

        Sale saleFound = saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Id not found, please check."));

        return saleFound;
    }

    @Override
    public Sale update(@NotNull Sale saleToUpdate) {

        Sale saleToBeUpdate = saleToUpdate.toBuilder()
                .updateDate(LocalDateTime.now()).build();

        Sale saleUpdated = saleRepository.save(saleToBeUpdate);

        return saleUpdated;
    }

    @Override
    public void deleteById(@NotNull Long id) {

        findById(id);
        saleRepository.deleteById(id);
    }

    @Override
    public List<Sale> findByClientId(Long id) {

        List<Sale> salesFound = saleRepository.findByClientId(id);

        return salesFound;
    }
}
