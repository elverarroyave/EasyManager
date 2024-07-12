package com.easymanager.easymanager.master.model;

import com.easymanager.easymanager.sale.model.Sale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="MASTER_BODY")
public class MasterBody implements Serializable {

    private static final long serialVersionUID = -1887053561143402117L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String key;

    @NotNull
    private String value;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="FK_HEAD")
    @JsonIgnore
    private MasterHead head;
    
    private LocalDateTime createdDate;

    private LocalDateTime updateDate;
}
