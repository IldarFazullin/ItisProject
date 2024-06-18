package com.example.ItisProject.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    @Column(name = "userName")
    private String userName;
    @Column(name = "Email", nullable = false)
    private String email;
    @Column(name = "Password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role userRole;
}
