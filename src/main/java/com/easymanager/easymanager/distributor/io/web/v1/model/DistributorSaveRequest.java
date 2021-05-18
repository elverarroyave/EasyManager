package com.easymanager.easymanager.distributor.io.web.v1.model;

import com.easymanager.easymanager.distributor.service.model.DistributorSaveCmd;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class DistributorSaveRequest {

    @NotNull(message = "Your number phone is required")
    @NotBlank(message = "Your number phone cannot be blank")
    private String name;

    @NotNull(message = "Your number phone is required")
    @NotBlank(message = "Your number phone cannot be blank")
    private String nit;

    @NotNull(message = "Your number phone is required")
    @NotBlank(message = "Your number phone cannot be blank")
    private String numberPhone;

    @Email
    @NotNull(message = "Your email is required")
    @NotBlank(message = "Your email cannot be blank")
    private String email;

    @NotNull(message = "Your address is required")
    @NotBlank(message = "Your address cannot be blank")
    private String address;

    public static DistributorSaveCmd fromModel(DistributorSaveRequest distributorSaveRequest){
        return DistributorSaveCmd.builder()
                .name(distributorSaveRequest.getName())
                .nit(distributorSaveRequest.getNit())
                .numberPhone(distributorSaveRequest.getNumberPhone())
                .email(distributorSaveRequest.getEmail())
                .address(distributorSaveRequest.getAddress())
                .build();
    }
}
