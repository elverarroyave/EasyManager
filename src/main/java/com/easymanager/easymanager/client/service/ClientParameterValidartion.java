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
            throw new BadRequestExeption("This email is already used by another client.");
        }

        //Validation document
        if(clientGateway.verifyDocument(client.getNumDocument()).isPresent()){
            throw new BadRequestExeption("This document is already used by another client.");
        }

    }
}
