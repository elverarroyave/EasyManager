package com.easymanager.easymanager.distributor.service.model;

import com.easymanager.easymanager.distributor.model.Distributor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class DistributorSaveCmd {

    private String name;

    private String nit;

    private String numberPhone;

    private String email;

    private String address;

    public static Distributor toModel(DistributorSaveCmd distributorSaveCmd){
        return Distributor.builder()
                .name(distributorSaveCmd.getName())
                .nit(distributorSaveCmd.getNit())
                .numberPhone(distributorSaveCmd.getNumberPhone())
                .email(distributorSaveCmd.getEmail())
                .address(distributorSaveCmd.getAddress())
                .build();
    }
}
