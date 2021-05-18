package com.easymanager.easymanager.distributor.service;

import com.easymanager.easymanager.distributor.model.Distributor;
import com.easymanager.easymanager.distributor.service.model.DistributorSaveCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DistributorServiceImpl implements DistributorService{

    @Autowired
    private DistributorGateway distributorGateway;


    @Override
    public Distributor save(@NotNull DistributorSaveCmd distributorSaveCmd) {

        Distributor distributorToSave = DistributorSaveCmd.toModel(distributorSaveCmd);

        Distributor distributorSaved = distributorGateway.save(distributorToSave);

        return distributorSaved;
    }

    @Override
    public Distributor findById(@NotNull Long id) {

        Distributor distributorFound = distributorGateway.findById(id);

        return distributorFound;
    }

    @Override
    public Distributor update(@NotNull Long id, DistributorSaveCmd distributorSaveCmd) {

        Distributor distributorInDataBase = distributorGateway.findById(id);

        Distributor distributorToUpdate = distributorInDataBase.toBuilder()
                .name(distributorSaveCmd.getName())
                .nit(distributorSaveCmd.getNit())
                .numberPhone(distributorSaveCmd.getNumberPhone())
                .email(distributorSaveCmd.getEmail())
                .address(distributorSaveCmd.getAddress())
                .build();

        Distributor distributorUpdated = distributorGateway.save(distributorToUpdate);

        return distributorUpdated;
    }

    @Override
    public List<Distributor> findAll() {

        List<Distributor> distributorsFound = distributorGateway.findAll();

        return distributorsFound;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        distributorGateway.deleteById(id);
    }

    @Override
    public Distributor findByNit(@NotNull String nit) {

        Distributor distributorFound = distributorGateway.findByNit(nit);

        return distributorFound;
    }
}
