package com.easymanager.easymanager.client.service;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.client.service.model.ClientSaveCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientGateway clientGateway;

    @Override
    public Client save(@NotNull ClientSaveCmd clientToCreate) {

        Client clientToSave = ClientSaveCmd.toModel(clientToCreate);

        return clientGateway.save(clientToSave);
    }

    @Override
    public List<Client> findAll() {
        return clientGateway.findAll();
    }

    @Override
    public Client findById(@NotNull Long id) {
        return clientGateway.findById(id);
    }

    @Override
    public Client update(@NotNull Long id, @NotNull ClientSaveCmd clientToUpdateCmd) {

        Client clientInDataBase = clientGateway.findById(id);

        Client clientToUpdate = clientInDataBase.toBuilder()
                .fullName(clientToUpdateCmd.getFullName())
                .numDocument(clientToUpdateCmd.getNumDocument())
                .numPhone(clientToUpdateCmd.getNumPhone())
                .email(clientToUpdateCmd.getEmail())
                .address(clientToUpdateCmd.getAddress())
                .build();

        return clientGateway.save(clientToUpdate);
    }

    @Override
    public void deleteById(@NotNull Long id) {
        clientGateway.deleteById(id);
    }
}
