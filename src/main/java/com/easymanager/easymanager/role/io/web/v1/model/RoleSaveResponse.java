package com.easymanager.easymanager.role.io.web.v1.model;

import com.easymanager.easymanager.role.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleSaveResponse {

    private String name;

    private String description;

    private boolean state;

    public static RoleSaveResponse fromModel(Role roleToResponse){
        return RoleSaveResponse.builder()
                .name(roleToResponse.getName())
                .description(roleToResponse.getDescription())
                .state(roleToResponse.isState())
                .build();
    }
}
