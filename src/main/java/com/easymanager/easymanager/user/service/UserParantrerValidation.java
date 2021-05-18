package com.easymanager.easymanager.user.service;


import com.easymanager.easymanager.config.exeption.BadRequestExeption;
import com.easymanager.easymanager.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserParantrerValidation{

    @Autowired
    private UserGateway userGateway;

    public void userValidation(User user){

        //Unique email user validation
        if(userGateway.verifyEmail(user.getEmail()).isPresent()){
            throw new BadRequestExeption("This emil is already used by another user.");
        }

    }


}
