package com.example.crud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "ecommerce3")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "google_id", length = 500)
    private String googleId;

    @Column(name = "role", length = 10)
    private String role;

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

}