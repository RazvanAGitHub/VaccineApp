package com.si.VaccineApp.domain;


import lombok.Data;

import javax.persistence.*;


@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true, length = 13)
    private String cnp;
    @Column(nullable = false)
    private String profession;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String familyDoctor;

    @OneToOne
    @JoinTable(
            name = "user_to_user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_role_id")}
    )
    private UserRole userRole;

}

