package com.easymanager.easymanager.sale.io.web.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleClientResponse {

    private Long id;

    private String document;

    private String name;

    private String lastName;

    private String numberPhone;

    private String address;

}
