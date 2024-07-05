package com.easymanager.easymanager.user;

import com.easymanager.easymanager.config.exeption.BadRequestExeption;
import com.easymanager.easymanager.role.service.RoleGateway;
import com.easymanager.easymanager.role.service.RoleService;
import com.easymanager.easymanager.user.model.User;
import com.easymanager.easymanager.user.service.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class UserValidations {

    public static User verifyUserMakeToSale(Long idUser, UserGateway userGateway, RoleGateway roleGateway){
        User userSeller = userGateway.findById(idUser);
        if(!userSeller.getRolesOfUser().contains(roleGateway.findById(3L))){
            throw new BadRequestExeption("Usuario no autorizado para realizar la venta");
        }
        return userSeller;
    }
}
