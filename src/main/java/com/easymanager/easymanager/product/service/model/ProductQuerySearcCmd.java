package com.easymanager.easymanager.product.service.model;

import lombok.*;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductQuerySearcCmd {
    private String name;

    private String code;

    private String category;
}
