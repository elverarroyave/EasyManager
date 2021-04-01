package com.easymanager.easymanager.client.model;

import com.easymanager.easymanager.sale.model.Sale;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CLIENT")
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String numDocument;

    private String numPhone;

    private String email;

    private String address;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL
    )
    private List<Sale> shopping = new ArrayList<>();

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
