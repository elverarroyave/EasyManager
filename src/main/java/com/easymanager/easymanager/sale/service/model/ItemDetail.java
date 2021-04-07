package com.easymanager.easymanager.sale.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDetail {

     Long productId;

     int quantity;

}
