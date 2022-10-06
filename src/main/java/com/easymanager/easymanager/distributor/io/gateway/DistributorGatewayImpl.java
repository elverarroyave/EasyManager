package com.easymanager.easymanager.distributor.io.gateway;

import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import com.easymanager.easymanager.distributor.io.repository.DistributorRepository;
import com.easymanager.easymanager.distributor.model.Distributor;
import com.easymanager.easymanager.distributor.service.DistributorGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public class DistributorGatewayImpl implements DistributorGateway {

    @Autowired
    DistributorRepository distributorRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Distributor save(@NotNull Distributor distributorToSave) {

        Distributor distributorToBeSave = distributorToSave.toBuilder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Distributor distributorSaved = distributorRepository.save(distributorToBeSave);

        return distributorSaved;
    }

    @Override
    public Distributor findById(@NotNull Long id) {

        Distributor foundDistributor = distributorRepository.findById(id)
                .orElseThrow(()-> new NotFoundExeption("Distribuidor con id: "+id+", no encontrado. Por favor revisar."));

        return foundDistributor;
    }

    @Override
    public Distributor update(@NotNull Distributor distributorToUpdate) {

        Distributor distributorToBeUpdate = distributorToUpdate.toBuilder()
                .updateDate(LocalDateTime.now())
                .build();

        Distributor distributorUpdated = save(distributorToBeUpdate);

        return distributorUpdated;
    }

    @Override
    public List<Distributor> findAll() {

        List<Distributor> foundDistributors = distributorRepository.findAll();

        return foundDistributors;
    }

    @Override
    public void deleteById(@NotNull Long id) {

        distributorRepository.deleteById(id);

    }

    @Override
    public Distributor findByNit(@NotNull String nit) {

        Distributor foundDistributor = distributorRepository.findByNit(nit)
                .orElseThrow(()-> new NotFoundExeption("Nit: "+nit+", no encontrado. Por favor revisar."));

        return foundDistributor;
    }
}
