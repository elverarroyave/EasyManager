package com.easymanager.easymanager.user.model;

import com.easymanager.easymanager.role.model.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "user_Id"),
            inverseJoinColumns = @JoinColumn(name = "role_Id")
    )
    private Set<Role> rolesOfUser;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public void addRole(Role roleToAdd){
        this.rolesOfUser.add(roleToAdd);
    }
}
