package com.example.crud.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "user", schema = "coolmate")
public class User  {


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "google_id", length = 500)
    private String googleId;

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "email", length = 350)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "height")
    private Integer height;

    @Column(name = "birth", length = 10)
    private String birth;

    @Column(name = "password", length = 500)
    private String password;


    public User() {

    }
}