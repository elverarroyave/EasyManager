package com.easymanager.easymanager.supplier.io.web.v1.model;

import com.easymanager.easymanager.supplier.model.Supplier;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SupplierSaveResponse {

    private Long id;

    private String name;

    private String nit;

    private String numberPhone;

    private String email;

    private String address;

    public static SupplierSaveResponse fromModel(Supplier supplierToResponnse){
        return SupplierSaveResponse.builder()
                .id(supplierToResponnse.getId())
                .name(supplierToResponnse.getName())
                .nit(supplierToResponnse.getNit())
                .numberPhone(supplierToResponnse.getNumberPhone())
                .email(supplierToResponnse.getEmail())
                .address(supplierToResponnse.getAddress())
                .build();
    }

    public static List<SupplierSaveResponse> fromModelList(List<Supplier> distributorsToResponse){

        List<SupplierSaveResponse> supplierSaveRespons = new ArrayList<>();

        for(Supplier supplier : distributorsToResponse){
            SupplierSaveResponse supplierSaveResponse = SupplierSaveResponse.fromModel(supplier);
            supplierSaveRespons.add(supplierSaveResponse);
        }
        return supplierSaveRespons;
    }

}
