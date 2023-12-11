package ru.Raingor.webAnimeSite.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    @NotEmpty(message = "The name must not be empty")
    @Size(min = 2
            , max = 200
            , message = "The name must have between two letters and two hundred letters in it")
    private String name;

    @Column(name = "password")
    @NotEmpty(message = "The password must not be empty")
    private String password;

    @Column(name = "email")
    @Email
    private String email;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

}
