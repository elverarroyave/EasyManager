package com.easymanager.easymanager.client.model;

import com.easymanager.easymanager.sale.model.Sale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

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
public class Client extends RepresentationModel<Client> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String numDocument;

    private String numberPhone;

    private String email;

    private String address;

    @OneToMany(
            fetch = FetchType.LAZY, mappedBy = "client"
    )
    @JsonIgnore
    private List<Sale> shopping = new ArrayList<>();

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
