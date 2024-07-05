package com.easymanager.easymanager.inventory.io.web.v1.model;

import com.easymanager.easymanager.inventory.service.model.InventorySaveCmd;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class InventorySaveRequest {
    private String productCode;
    private int baseStock;

    public static InventorySaveCmd fromModel(InventorySaveRequest inventorySaveRequest){
        return InventorySaveCmd.builder()
                .productCode(inventorySaveRequest.getProductCode())
                .baseStock(inventorySaveRequest.getBaseStock())
                .build();
    }
}
