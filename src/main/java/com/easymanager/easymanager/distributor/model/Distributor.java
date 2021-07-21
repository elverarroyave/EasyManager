package com.easymanager.easymanager.distributor.model;

import com.easymanager.easymanager.order.model.Orden;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Entity(name = "DISTRIBUTOR")
@Table(name = "DISTRIBUTOR")
public class Distributor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String nit;

    private String numberPhone;

    private String email;

    private String address;

    @OneToMany(
            fetch = FetchType.LAZY, mappedBy = "distributor"
    )
    @JsonIgnore
    private List<Orden> ordens = new ArrayList<>();

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
