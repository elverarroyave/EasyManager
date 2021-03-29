package com.easymanager.easymanager.role.model;

import com.easymanager.easymanager.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ROLE")
@Table(name = "ROLE")
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "state")
    private boolean state;

    /*
    @ManyToMany(mappedBy = "rolesOfUser")
    private Set<User> users;
     */
}
