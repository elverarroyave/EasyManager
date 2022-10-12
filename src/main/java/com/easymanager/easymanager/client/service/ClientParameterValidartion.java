package com.easymanager.easymanager.client.service;

import com.easymanager.easymanager.client.model.Client;
import com.easymanager.easymanager.config.exeption.BadRequestExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientParameterValidartion {

    @Autowired
    private ClientGateway clientGateway;

    void parametersValidation(Client client){

        //Validation email
        if(clientGateway.verifyEmail(client.getEmail()).isPresent()){
            throw new BadRequestExeption("El correo: " +client.getEmail() + ", ya es usado por otro cliente.");
        }

        //Validation document
        if(clientGateway.verifyDocument(client.getNumDocument()).isPresent()){
            throw new BadRequestExeption("El número de documento: "+client.getNumDocument()+", ya se encuentra registrado en el sistema.");
        }

    }
}
