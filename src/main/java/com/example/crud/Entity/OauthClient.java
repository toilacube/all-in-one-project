package com.example.crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "oauth_clients", schema = "ecommerce3")
public class OauthClient {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "secret", length = 100)
    private String secret;

    @Column(name = "provider")
    private String provider;

    @Lob
    @Column(name = "redirect", nullable = false)
    private String redirect;

    @Column(name = "personal_access_client", nullable = false)
    private Boolean personalAccessClient = false;

    @Column(name = "password_client", nullable = false)
    private Boolean passwordClient = false;

    @Column(name = "revoked", nullable = false)
    private Boolean revoked = false;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}