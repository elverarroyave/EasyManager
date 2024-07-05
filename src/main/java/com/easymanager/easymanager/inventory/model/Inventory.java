package com.easymanager.easymanager.inventory.model;

import com.easymanager.easymanager.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "INVENTORY")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int stock;
    private int baseStock;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public void updateStock(int amount){
        this.stock += amount;
    }
}
