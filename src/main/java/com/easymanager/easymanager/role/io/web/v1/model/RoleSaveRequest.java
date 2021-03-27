package com.easymanager.easymanager.role.io.web.v1.model;

import com.easymanager.easymanager.role.service.model.RoleSaveCmd;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RoleSaveRequest {

    @NotNull(message = "The name cannot be blank")
    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(message = "The description cannot be blank")
    @NotBlank(message = "The description is required")
    private String description;

    private boolean state;

    public static RoleSaveCmd toModel(RoleSaveRequest roleToCreate){
        return RoleSaveCmd.builder()
                .name(roleToCreate.getName())
                .description(roleToCreate.getDescription())
                .status(roleToCreate.isState())
                .build();
    }

}
