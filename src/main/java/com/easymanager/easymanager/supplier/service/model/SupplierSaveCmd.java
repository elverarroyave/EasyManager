package com.easymanager.easymanager.supplier.service.model;

import com.easymanager.easymanager.supplier.model.Supplier;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierSaveCmd {

    private String name;

    private String nit;

    private String numberPhone;

    private String email;

    private String address;

    public static Supplier toModel(SupplierSaveCmd supplierSaveCmd){
        return Supplier.builder()
                .name(supplierSaveCmd.getName())
                .nit(supplierSaveCmd.getNit())
                .numberPhone(supplierSaveCmd.getNumberPhone())
                .email(supplierSaveCmd.getEmail())
                .address(supplierSaveCmd.getAddress())
                .build();
    }
}
