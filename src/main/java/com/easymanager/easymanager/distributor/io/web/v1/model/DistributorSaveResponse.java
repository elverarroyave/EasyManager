package com.easymanager.easymanager.distributor.io.web.v1.model;

import com.easymanager.easymanager.distributor.model.Distributor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DistributorSaveResponse {

    private Long id;

    private String name;

    private String nit;

    private String numberPhone;

    private String email;

    private String address;

    public static DistributorSaveResponse fromModel(Distributor distributorToResponnse){
        return DistributorSaveResponse.builder()
                .id(distributorToResponnse.getId())
                .name(distributorToResponnse.getName())
                .nit(distributorToResponnse.getNit())
                .numberPhone(distributorToResponnse.getNumberPhone())
                .email(distributorToResponnse.getEmail())
                .address(distributorToResponnse.getAddress())
                .build();
    }

    public static List<DistributorSaveResponse> fromModelList(List<Distributor> distributorsToResponse){

        List<DistributorSaveResponse> distributorSaveResponses = new ArrayList<>();

        for(Distributor distributor : distributorsToResponse){
            DistributorSaveResponse distributorSaveResponse = DistributorSaveResponse.fromModel(distributor);
            distributorSaveResponses.add(distributorSaveResponse);
        }
        return distributorSaveResponses;
    }

}
