package com.easymanager.easymanager.role.service.model;

import com.easymanager.easymanager.role.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleSaveCmd {

    private String name;

    private String description;

    private boolean status;

    public static Role toModel(RoleSaveCmd roleToSaveCmd){
        return Role.builder()
                .name(roleToSaveCmd.getName())
                .description(roleToSaveCmd.getDescription())
                .state(roleToSaveCmd.isStatus())
                .build();
    }

}
