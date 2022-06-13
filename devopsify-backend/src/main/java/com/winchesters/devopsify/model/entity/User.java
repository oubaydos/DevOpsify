package com.winchesters.devopsify.model.entity;


import com.winchesters.devopsify.model.GithubCredentials;
import com.winchesters.devopsify.security.ApplicationUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private GithubCredentials githubCredentials;

    @OneToMany(mappedBy="owner", fetch = FetchType.LAZY)
    private List<Project> projects = new ArrayList<>();

    public User(Long userId, String username, String email, String password, ApplicationUserRole role, GithubCredentials githubCredentials) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.githubCredentials = githubCredentials;
    }
}
