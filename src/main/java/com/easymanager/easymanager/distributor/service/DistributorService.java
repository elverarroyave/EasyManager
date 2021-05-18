package com.easymanager.easymanager.distributor.service;

import com.easymanager.easymanager.distributor.model.Distributor;
import com.easymanager.easymanager.distributor.service.model.DistributorSaveCmd;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface DistributorService {

    Distributor save(@NotNull DistributorSaveCmd distributorSaveCmd);

    Distributor findById(@NotNull Long id);

    Distributor update(@NotNull Long id, DistributorSaveCmd distributorSaveCmd);

    List<Distributor> findAll();

    void deleteById(@NotNull Long id);

    Distributor findByNit(@NotNull String nit);

}
