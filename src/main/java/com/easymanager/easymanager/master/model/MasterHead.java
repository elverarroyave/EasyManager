package com.easymanager.easymanager.master.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MASTER_HEAD")
public class MasterHead implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private static final long serialVersionUID = 6050836334896394721L;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;
}
