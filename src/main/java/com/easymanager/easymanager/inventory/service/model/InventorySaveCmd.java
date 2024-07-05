package com.easymanager.easymanager.inventory.service.model;

import com.easymanager.easymanager.inventory.model.Inventory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class InventorySaveCmd {
    private String productCode;
    private int stock;
    private int baseStock;

    public static Inventory fromModel(InventorySaveCmd inventorySaveCmd){
        return Inventory.builder()
                .stock(inventorySaveCmd.getStock())
                .baseStock(inventorySaveCmd.getBaseStock())
                .build();
    }
}
