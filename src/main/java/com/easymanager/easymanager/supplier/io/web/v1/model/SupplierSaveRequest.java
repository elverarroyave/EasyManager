package com.easymanager.easymanager.supplier.io.web.v1.model;

import com.easymanager.easymanager.supplier.service.model.SupplierSaveCmd;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class SupplierSaveRequest {

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

    public static SupplierSaveCmd fromModel(SupplierSaveRequest supplierSaveRequest){
        return SupplierSaveCmd.builder()
                .name(supplierSaveRequest.getName())
                .nit(supplierSaveRequest.getNit())
                .numberPhone(supplierSaveRequest.getNumberPhone())
                .email(supplierSaveRequest.getEmail())
                .address(supplierSaveRequest.getAddress())
                .build();
    }
}
