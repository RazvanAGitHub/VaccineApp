package com.si.VaccineApp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "user_roles")
@Entity
public class UserRole {
    @Id
    @Column(length = 20)
    @Enumerated(value = EnumType.STRING) // ADMIN, CLIENT
    private Role name;

    @OneToMany
    @JoinTable(
            name = "user_to_user_roles",
            joinColumns = {@JoinColumn(name = "user_role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

}
