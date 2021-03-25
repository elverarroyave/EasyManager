package com.easymanager.easymanager.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String password;

    private String typeDocument;

    private String numDocument;

    private String numberPhone;

    private String email;

    private String address;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
