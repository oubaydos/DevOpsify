package com.winchesters.devopsify.model.entity;


import com.winchesters.devopsify.security.ApplicationUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MY_USER")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotNull(message = "username must not be null")
    @Column(name = "username", unique = true)
    private String username;

    @NotNull(message = "Email must not be null")
    @Column(name = "email", unique = true)
    @javax.validation.constraints.Email(message = "not an email")
    private String email;

    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be an empty string")
    private String password;

    @NotNull(message = "role must not be null")
    @Enumerated(EnumType.STRING)
    private ApplicationUserRole role;

    private String PersonalAccessToken;
}
