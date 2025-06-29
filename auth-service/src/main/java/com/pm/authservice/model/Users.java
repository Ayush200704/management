package com.pm.authservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
}
