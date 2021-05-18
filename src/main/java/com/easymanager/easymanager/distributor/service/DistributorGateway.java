package com.easymanager.easymanager.distributor.service;

import com.easymanager.easymanager.distributor.model.Distributor;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DistributorGateway {

    Distributor save(@NotNull Distributor distributorToSave);

    Distributor findById(@NotNull Long id);

    Distributor update(@NotNull Distributor distributorToUpdate);

    List<Distributor> findAll();

    void deleteById(@NotNull Long id);

    Distributor findByNit(@NotNull String nit);

}
